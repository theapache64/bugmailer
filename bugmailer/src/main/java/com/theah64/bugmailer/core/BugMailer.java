package com.theah64.bugmailer.core;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;

import com.theah64.bugmailer.exceptions.BugMailerException;
import com.theah64.bugmailer.models.BoldNode;
import com.theah64.bugmailer.models.Node;
import com.theah64.bugmailer.utils.CommonUtils;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by shifar on 15/4/16.
 * LOGO :  https://i.stack.imgur.com/21iAR.png
 * Header: https://i.stack.imgur.com/U2JVG.png
 */
public class BugMailer {

    private static final String DEFAULT_THEME_COLOR = Colors.MATERIAL_RED_500;

    private static final String X = BugMailer.class.getSimpleName();
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
    }


    /*private static void sendMail(final String message, final String subject) {

        if (session == null) {

            final Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.socketFactory.port", "465");
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");


            session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(gmailUsername, gmailPassword);
                }
            });

        }


        new Thread(new Runnable() {

            @Override
            public void run() {

                Message mimeMessage = new MimeMessage(session);
                try {
                    mimeMessage.setFrom(new InternetAddress(gmailUsername));

                    final List<String> recipients = config.getRecipients();

                    mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients.get(0)));

                    if (recipients.size() > 1) {
                        //has ccs
                        final StringBuilder ccBuilder = new StringBuilder();
                        for (int i = 1; i < recipients.size(); i++) {
                            ccBuilder.append(recipients.get(i)).append(",");
                        }

                        mimeMessage.addRecipients(Message.RecipientType.CC, InternetAddress.parse(ccBuilder.toString().substring(0, ccBuilder.length() - 1)));
                    }

                    mimeMessage.setFrom(new InternetAddress(gmailUsername, projectName + " - BugMailer"));
                    mimeMessage.setSubject(subject);
                    mimeMessage.setContent(message, "text/html; charset=utf-8");

                    Transport.send(mimeMessage);

                    System.out.println("BugReport sent\n" + message);

                } catch (MessagingException | UnsupportedEncodingException e) {
                    e.printStackTrace();
                    System.out.println("Failed to send mail");
                }
            }
        }).start();
    }*/


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

        final String errorReport = new ReportGenerator(BugMailer.getProjectName(), BugMailer.getPackageName(), primaryStackLine)
                .addNode(new BoldNode("Fatal error", primaryStackLineHTML))
                .addNode(new Node("App version name", BugMailer.getAppVersionName()))
                .addNode(new Node("App version code", BugMailer.getAppVersionCode()))
                .addNode(new Node("Filename", e.getStackTrace()[0].getFileName()))
                .addNode(new Node("API Level", Build.VERSION.SDK_INT))
                .addNode(new Node("Time of occurrence", new Date().toString()))
                .addNode(new Node("Device", Build.DEVICE))
                .addNode(new Node("Model", Build.MODEL))
                .addNode(new Node("Product", Build.PRODUCT))
                .addNode(new Node("Exception message", stackTraceBuilder.toString()))
                .addCustomNode(customNode)
                .build();

        //Building to list
        final List<String> recipients = config.getRecipients();
        final StringBuilder ccBuilder = new StringBuilder();
        for (int i = 0; i < recipients.size(); i++) {
            ccBuilder.append(recipients.get(i)).append(",");
        }

        sendMail(ccBuilder.substring(0, ccBuilder.length() - 1), errorReport, primaryStackLine);

    }

    private static final OkHttpClient okHttpClient = new OkHttpClient();

    private static void sendMail(final String to, String message, String subject) {

        final Request request = new Request.Builder()
                .addHeader("Authorization", "1KGZGdZvgM")
                .url("http://theapache64.xyz:8080/safemail/v1/sendMail")
                .post(new FormBody.Builder()
                        .add("from_personal", projectName + " - BugMailer")
                        .add("to", to)
                        .add("subject", subject)
                        .add("message", message)
                        .build())
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, @NonNull Response response) throws IOException {
                System.out.println(response.body().string());
            }
        });
    }

    private static int getAppVersionCode() {
        return appVersionCode;
    }
}
