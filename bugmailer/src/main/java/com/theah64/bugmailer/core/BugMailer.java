package com.theah64.bugmailer.core;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import com.theah64.bugmailer.exceptions.BugMailerException;
import com.theah64.bugmailer.models.BoldNode;
import com.theah64.bugmailer.models.Node;
import com.theah64.bugmailer.models.Recipient;
import com.theah64.bugmailer.utils.CommonUtils;
import com.theah64.bugmailer.utils.SecretConstants;
import com.theah64.safemail.SafeMail;
import com.theapache64.github_android_sdk.GitHubAPI;
import com.theapache64.github_android_sdk.responses.CreateCommentResponse;
import com.theapache64.github_android_sdk.responses.CreateIssueResponse;
import com.theapache64.github_android_sdk.responses.ListIssuesResponse;

import java.util.Date;
import java.util.List;

/**
 * Created by shifar on 15/4/16.
 * LOGO :  https://i.stack.imgur.com/21iAR.png
 * Header: https://i.stack.imgur.com/U2JVG.png
 */
public class BugMailer {

    private static final String DEFAULT_THEME_COLOR = Colors.MATERIAL_RED_500;

    private static final String X = BugMailer.class.getSimpleName();
    private static final String SAFE_MAIL_API_KEY = "NsufXcUuoa";
    private static String projectName;
    private static String packageName;
    private static String appVersionName;
    private static String themeColor;
    private static BugMailerConfig config;
    private static int appVersionCode;


    public static void init(final Context context, final BugMailerConfig config) throws BugMailerException {

        BugMailer.packageName = context.getPackageName();

        final PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            throw new BugMailerException("Couldn't find package name. Default 'Unknown' set");
        }

        BugMailer.appVersionName = packageInfo.versionName;
        BugMailer.appVersionCode = packageInfo.versionCode;
        BugMailer.projectName = (String) context.getApplicationInfo().loadLabel(context.getPackageManager());
        BugMailer.themeColor = config.getThemeColor() == null ? DEFAULT_THEME_COLOR : config.getThemeColor();


        Thread.setDefaultUncaughtExceptionHandler(new CustomExceptionHandler());
        BugMailer.config = config;

        //Initializing safemail
        SafeMail.init(SAFE_MAIL_API_KEY);

        //Initializing Github SDK
        GitHubAPI.init(SecretConstants.GITHUB_ACCESS_TOKEN);
    }


    private static String getProjectName() {
        return projectName;
    }

    private static String getPackageName() {
        return packageName;
    }

    private static String getAppVersionName() {
        return appVersionName;
    }

    public static void report(Throwable e) {
        report(e, null);
    }

    public static void setThemeColor(String themeColor) {
        BugMailer.themeColor = themeColor;
    }

    public static String getThemeColor() {
        return themeColor;
    }

    public static void report(Throwable e, BugMailerNode customNode) {


        //Getting stack trace
        final String stackTrace = CommonUtils.getStackTrace(e);
        final String[] stackLines = stackTrace.split("\n");
        final String primaryStackLine = stackLines.length > 1 ? stackLines[0] + " " + stackLines[1].trim() : stackTrace;
        final String primaryStackLineHTML = String.format("<span style='color:#THEMECOLOR;'>%s %s</span>", stackLines[0], stackLines[1].trim());

        final StringBuilder stackTraceBuilder = new StringBuilder();
        for (final String stackLine : stackLines) {
            if (stackLine.contains(BugMailer.getPackageName())) {
                stackTraceBuilder.append("<span style='color:#THEMECOLOR;'><b>").append(stackLine).append("<b></span>");
            } else {
                stackTraceBuilder.append(stackLine);
            }

            stackTraceBuilder.append("<br>");
        }

        final String fileName = e.getStackTrace()[0].getFileName();
        final String errorReport = new ReportGenerator(BugMailer.getProjectName(), BugMailer.getPackageName(), primaryStackLine)
                .addNode(new BoldNode("Fatal Error", primaryStackLineHTML))
                .addNode(new Node("App Version Name", BugMailer.getAppVersionName()))
                .addNode(new Node("App Version Code", BugMailer.getAppVersionCode()))
                .addNode(new Node("File Name", fileName))
                .addNode(new Node("API Level", Build.VERSION.SDK_INT))
                .addNode(new Node("Time of Occurrence", new Date().toString()))
                .addNode(new Node("Device", Build.DEVICE))
                .addNode(new Node("Model", Build.MODEL))
                .addNode(new Node("Product", Build.PRODUCT))
                .addNode(new Node("Exception Message", stackTraceBuilder.toString()))
                .addCustomNode(customNode)
                .build();

        //Building to list
        final List<Recipient> recipients = config.getRecipients();
        final StringBuilder ccBuilder = new StringBuilder();
        for (int i = 0; i < recipients.size(); i++) {

            final Recipient recipient = recipients.get(i);

            if (recipient.getExceptionClass().isAssignableFrom(e.getClass())) {
                ccBuilder.append(recipients.get(i).getEmail()).append(",");
            }

        }

        System.out.println("To: " + ccBuilder.toString());


        SafeMail.sendMail(
                projectName + " - BugMailer",
                ccBuilder.substring(0, ccBuilder.length() - 1),
                primaryStackLine,
                errorReport
        );

        if (config.isGitHubIssueTracker()) {

            final String title = String.format("%s:%s",
                    primaryStackLine,
                    BugMailer.appVersionCode
            );

            //Building body
            final GitHubCommentGenerator gitHubCommentGenerator = new GitHubCommentGenerator();
            gitHubCommentGenerator.addNode("Fatal Error", primaryStackLineHTML)
                    .addNode("App Version Name", BugMailer.getAppVersionName())
                    .addNode("App Version Code", BugMailer.getAppVersionCode())
                    .addNode("File Name", fileName)
                    .addNode("API Level", Build.VERSION.SDK_INT)
                    .addNode("Time of Occurrence", new Date().toString())
                    .addNode("Device", Build.DEVICE)
                    .addNode("Model", Build.MODEL)
                    .addNode("Product", Build.PRODUCT)
                    .addNode("Exception Message", stackTraceBuilder.toString());

            if (customNode != null) {
                for (int i = 0; i < customNode.getNodes().size(); i++) {
                    Node x = customNode.getNodes().get(i);
                    gitHubCommentGenerator.addNode(x.getKey(), x.getValue());
                }
            }

            GitHubAPI.listIssues(config.getOwner(), config.getRepo(), new GitHubAPI.Callback<List<ListIssuesResponse.Issue>>() {
                @Override
                public void onSuccess(List<ListIssuesResponse.Issue> issues) {

                    int issueNumber = -1;

                    for (final ListIssuesResponse.Issue issue : issues) {
                        if (issue.getTitle().equals(title)) {
                            issueNumber = issue.getNumber();
                            break;
                        }
                    }

                    if (issueNumber != -1) {
                        //New comment
                        GitHubAPI.createComment(config.getOwner(), config.getRepo(), issueNumber, gitHubCommentGenerator.toString(), new GitHubAPI.Callback<CreateCommentResponse>() {
                            @Override
                            public void onSuccess(CreateCommentResponse createCommentResponse) {
                                System.out.println("Added comment");
                            }

                            @Override
                            public void onError(Throwable t) {

                            }
                        });
                    } else {
                        //New issue
                        GitHubAPI.createIssue(config.getOwner(), config.getRepo(), title, gitHubCommentGenerator.toString(), new GitHubAPI.Callback<CreateIssueResponse>() {
                            @Override
                            public void onSuccess(CreateIssueResponse createIssueResponse) {
                                System.out.println("New issue added");
                            }

                            @Override
                            public void onError(Throwable t) {

                            }
                        });
                    }

                }

                @Override
                public void onError(Throwable t) {

                }
            });

        }

    }

    private static int getAppVersionCode() {
        return appVersionCode;
    }
}
