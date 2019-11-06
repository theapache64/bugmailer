package com.theah64.bugmailer.core

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import com.theah64.bugmailer.exceptions.BugMailerException
import com.theah64.bugmailer.models.BoldNode
import com.theah64.bugmailer.models.Node
import com.theah64.bugmailer.utils.CommonUtils
import com.theah64.bugmailer.utils.SecretConstants
import com.theah64.safemail.SafeMail
import com.theapache64.github_android_sdk.GitHubAPI
import com.theapache64.github_android_sdk.responses.CreateCommentResponse
import com.theapache64.github_android_sdk.responses.CreateIssueResponse
import com.theapache64.github_android_sdk.responses.ListIssuesResponse
import java.util.*

/**
 * Created by shifar on 15/4/16.
 * LOGO :  https://i.stack.imgur.com/21iAR.png
 * Header: https://i.stack.imgur.com/U2JVG.png
 */
object BugMailer {

    private val DEFAULT_THEME_COLOR = Colors.MATERIAL_RED_500

    private val X = BugMailer::class.java.simpleName
    private val SAFE_MAIL_API_KEY = "NsufXcUuoa"
    private val KEY_EXCEPTION_MESSAGE = "Exception Message"
    private lateinit var projectName: String
    private lateinit var packageName: String
    private var appVersionName: String? = null
    var themeColor: String? = null
    private var config: BugMailerConfig? = null
    private var appVersionCode: Int = 0


    @Throws(BugMailerException::class)
    fun init(context: Context, config: BugMailerConfig) {

        packageName = context.packageName

        val packageInfo: PackageInfo
        try {
            packageInfo = context.packageManager.getPackageInfo(packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            throw BugMailerException("Couldn't find package name. Default 'Unknown' set")
        }

        appVersionName = packageInfo.versionName
        appVersionCode = packageInfo.versionCode
        projectName = context.applicationInfo.loadLabel(context.packageManager) as String
        themeColor = if (config.themeColor == null) DEFAULT_THEME_COLOR else config.themeColor


        Thread.setDefaultUncaughtExceptionHandler(CustomExceptionHandler())
        BugMailer.config = config

        //Initializing safemail
        SafeMail.init(SAFE_MAIL_API_KEY)

        //Initializing Github SDK
        GitHubAPI.init(SecretConstants.GITHUB_ACCESS_TOKEN)
    }

    @JvmOverloads
    fun report(e: Throwable, customNode: BugMailerNode? = null) {


        //Getting stack trace
        val stackTrace = CommonUtils.getStackTrace(e)
        val stackLines = stackTrace.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val primaryStackLine = if (stackLines.size > 1) stackLines[0] + " " + stackLines[1].trim { it <= ' ' } else stackTrace
        val primaryStackLineHTML = String.format("<span style='color:#THEMECOLOR;'>%s %s</span>", stackLines[0], stackLines[1].trim { it <= ' ' })

        val stackTraceBuilderHtml = StringBuilder()
        val simpleStackTraceBuilder = StringBuilder()
        for (stackLine in stackLines) {
            if (stackLine.contains(packageName!!)) {
                stackTraceBuilderHtml.append("<span style='color:#THEMECOLOR;'><b>").append(stackLine).append("<b></span>")
            } else {
                stackTraceBuilderHtml.append(stackLine)
            }

            simpleStackTraceBuilder.append(stackLine).append("\n")

            stackTraceBuilderHtml.append("<br>")
        }

        val fileName = e.stackTrace[0].fileName

        //Data
        val dataNodes = ArrayList<Node>()
        dataNodes.add(BoldNode("Fatal Error", primaryStackLineHTML))
        dataNodes.add(Node("App Version Name", appVersionName!!))
        dataNodes.add(Node("App Version Code", appVersionCode))
        dataNodes.add(Node("File Name", fileName))
        dataNodes.add(Node("API Level", Build.VERSION.SDK_INT))
        dataNodes.add(Node("Time of Occurrence", Date().toString()))
        dataNodes.add(Node("Device", Build.DEVICE))
        dataNodes.add(Node("Model", Build.MODEL))
        dataNodes.add(Node("Product", Build.PRODUCT))
        dataNodes.add(Node(KEY_EXCEPTION_MESSAGE, stackTraceBuilderHtml.toString()))

        if (customNode != null) {
            dataNodes.addAll(customNode.nodes)
        }


        val errorReportGen = ReportGenerator(projectName, packageName, primaryStackLine)

        for (node in dataNodes) {
            errorReportGen.addNode(node)
        }

        val errorReport = errorReportGen.build()

        //Building to list
        val recipients = config!!.recipients
        val ccBuilder = StringBuilder()
        for (i in recipients.indices) {

            val recipient = recipients[i]

            if (recipient.exceptionClass.isAssignableFrom(e.javaClass)) {
                ccBuilder.append(recipients[i].email).append(",")
            }

        }

        println("To: $ccBuilder")


        SafeMail.sendMail(
                projectName + " - BugMailer",
                ccBuilder.substring(0, ccBuilder.length - 1),
                primaryStackLine,
                errorReport
        )

        if (config!!.isGitHubIssueTracker) {

            println("Igniting GitHub issue management system")

            val title = String.format("%s:%s",
                    primaryStackLine,
                    appVersionCode
            )

            println("Issue title: $title")


            //Building body
            val gitHubCommentGenerator = GitHubCommentGenerator()

            for (node in dataNodes) {
                if (node.key == KEY_EXCEPTION_MESSAGE) {
                    gitHubCommentGenerator.addCodeNode(node.key, simpleStackTraceBuilder.toString())
                } else {
                    gitHubCommentGenerator.addNode(node.key, (node as? BoldNode)?.value?.replace("(<b>|</b>)".toRegex(), "")
                            ?: node.value)
                }
            }

            GitHubAPI.listIssues(config!!.owner, config!!.repo, GitHubAPI.IssueType.ALL, object : GitHubAPI.Callback<List<ListIssuesResponse.Issue>> {
                override fun onSuccess(issues: List<ListIssuesResponse.Issue>) {

                    var issueNumber = -1

                    for (issue in issues) {
                        if (issue.title == title) {
                            issueNumber = issue.number
                            break
                        }
                    }

                    if (issueNumber != -1) {
                        //New comment
                        GitHubAPI.createComment(config!!.owner, config!!.repo, issueNumber, gitHubCommentGenerator.toString(), object : GitHubAPI.Callback<CreateCommentResponse> {
                            override fun onSuccess(createCommentResponse: CreateCommentResponse) {
                                println("Added comment")
                            }

                            override fun onError(t: Throwable) {

                            }
                        })
                    } else {
                        //New issue
                        GitHubAPI.createIssue(config!!.owner, config!!.repo, title, gitHubCommentGenerator.toString(), object : GitHubAPI.Callback<CreateIssueResponse> {
                            override fun onSuccess(createIssueResponse: CreateIssueResponse) {
                                println("New issue added")
                            }

                            override fun onError(t: Throwable) {

                            }
                        })
                    }

                }

                override fun onError(t: Throwable) {

                }
            })

        }

    }
}
