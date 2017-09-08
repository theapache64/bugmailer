package com.theah64.bugmailer;

import android.util.Log;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by shifar on 15/4/16.
 * LOGO :  https://i.stack.imgur.com/21iAR.png
 * Header: https://i.stack.imgur.com/U2JVG.png
 */
public class BugMailer {

    private static String gmailUsername, gmailPassword;
    private static String reportToEmail;
    private static Session session;


    public static void init(final String gmailUsername, final String gmailPassword, final String reportToEmail) {
        BugMailer.gmailUsername = gmailUsername;
        BugMailer.gmailPassword = gmailPassword;
        BugMailer.reportToEmail = reportToEmail;

        Thread.setDefaultUncaughtExceptionHandler(new CustomExceptionHandler());
    }

    private static class CustomExceptionHandler implements Thread.UncaughtExceptionHandler {

        private static final String X = CustomExceptionHandler.class.getSimpleName();
        private Thread.UncaughtExceptionHandler defaultUEH;

        CustomExceptionHandler() {
            this.defaultUEH = Thread.getDefaultUncaughtExceptionHandler();
        }

        public void uncaughtException(Thread t, final Throwable e) {
            Log.d(X, "Caught uncaught exception");
            sendMail(e.getMessage());
            defaultUEH.uncaughtException(t, e);
        }
    }


    public static void sendMail(final String message) {

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

                System.out.println("Sending email to " + reportToEmail);

                System.out.println("u:" + gmailUsername);
                System.out.println("p:" + gmailPassword);


                Message mimeMessage = new MimeMessage(session);
                try {
                    mimeMessage.setFrom(new InternetAddress(gmailUsername));
                    mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(reportToEmail));
                    mimeMessage.setSubject("Bug Report - BugMailer");
                    mimeMessage.setContent("<div class=\"msg\"><div style=\"font-size:16px;border:1px solid #e0e0e0;max-width:800px;font-family:roboto,sans-serif;font-weight:400\"><div style=\"padding:20px 0px 20px 0px;text-align:center;background-color:#ffffff\"><img src=\"https://ci5.googleusercontent.com/proxy/Hlzsx5NFzJIunKNovSLx3xhzFAPNVTyg2ySF6y4QcTAxRcT04yuYtpxQK8NfLyH_UD3evMHQopvC7kMemL0ZlR5-RYUF7VfnMjlkoz9RdfjHC4yW4UgtN3_cFEU18uM=s0-d-e1-ft#https://firebase.google.com/_static/696d99a5ad/images/firebase/lockup.png\" style=\"width:145px;min-height:40px\"></div><div style=\"padding:24px 24px 24px 24px;background-color:#039be5;font-family:roboto,sans-serif;font-weight:400\"><table style=\"width:100%;font-family:roboto,sans-serif;font-weight:400\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><td><div style=\"font-size:14px;line-height:16px;color:#b3e1f7\">anona-705d7</div><div style=\"font-size:20px;line-height:24px;color:#ffffff\">3 new issues for com.cybaze.goodbuy - Android</div></td><td><div style=\"text-align:right\"><img src=\"https://ci6.googleusercontent.com/proxy/Cvj52vFVxSfNWI4XcQgZJvyDSATy77fAyZvSRAMKVOONvK0uQn8NBMx1LyeaDGUkZ7c8xyQ-UiwGXlRWUvEpiia0Euc3wL-kBn8aWPjlGZmGgxBZpTyJRZgJuD9RVkPun0e9m8odWw=s0-d-e1-ft#https://www.gstatic.com/images/icons/material/system/2x/bug_report_white_24dp.png\" style=\"min-height:24px;width:24px\"></div></td></tr></tbody></table></div><div style=\"font-family:roboto,sans-serif;font-weight:400\"><div style=\"font-size:14px;line-height:20px;color:#212121;font-family:roboto,sans-serif;font-weight:400;margin:24px 24px 0px 24px\">Your app recently experienced a few issues that haven't been seen before. Here are some details below.</div><div style=\"padding-left:20px;font-size:14px;line-height:16px;padding-top:12px;padding-bottom:34px;padding-top:34px;text-align:right;font-family:roboto,sans-serif;font-weight:500\"><a href=\"https://console.firebase.google.com/project/anona-705d7/monitoring/app/android:com.cybaze.goodbuy/crash?utm_source=alerts&amp;utm_medium=email&amp;utm_campaign=crash&amp;utm_content=dashboard\" style=\"text-decoration:none;margin-right:24px;padding:12px 12px 12px 12px;border:0;background-color:#039be5;color:#ffffff;font-family:roboto,sans-serif;font-weight:500;border-radius:2px\" target=\"_blank\" rel=\"noreferrer\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=https://console.firebase.google.com/project/anona-705d7/monitoring/app/android:com.cybaze.goodbuy/crash?utm_source%3Dalerts%26utm_medium%3Demail%26utm_campaign%3Dcrash%26utm_content%3Ddashboard&amp;source=gmail&amp;ust=1504955366065000&amp;usg=AFQjCNG1nAcXO4depFtPhaCcHlfG_Zyk4Q\">VIEW DASHBOARD</a></div><hr style=\"display:block;border:0;border-top:1px solid #e0e0e0;margin:0px 24px 0px 24px\"><div style=\"background-color:#ffffff;margin:0px 0px 0px 0px;font-size:14px;font-family:roboto,sans-serif;font-weight:400\"><div style=\"padding:22px 24px 0px 0px;font-family:roboto,sans-serif;font-weight:400\"><div style=\"font-size:14px;line-height:20px;font-family:roboto,sans-serif;font-weight:400;color:#757575;padding:0px 0px 0px 24px\">Fatal error</div><div style=\"padding:0px 0px 0px 24px\"><table style=\"width:100%;font-family:roboto,sans-serif;font-weight:400;font-size:14px\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><td><div>java.lang.<wbr>IllegalArgumentException</div></td></tr></tbody></table></div></div><div style=\"padding:22px 24px 0px 0px;font-family:roboto,sans-serif;font-weight:400\"><div style=\"font-size:14px;line-height:20px;font-family:roboto,sans-serif;font-weight:400;color:#757575;padding:0px 0px 0px 24px\">First seen version</div><div style=\"padding:0px 0px 0px 24px\"><table style=\"width:100%;font-family:roboto,sans-serif;font-weight:400;font-size:14px\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><td><div><a style=\"color:#212121\" rel=\"noreferrer\">1</a></div></td></tr></tbody></table></div></div><div style=\"padding:22px 24px 0px 0px;font-family:roboto,sans-serif;font-weight:400\"><div style=\"font-size:14px;line-height:20px;font-family:roboto,sans-serif;font-weight:400;color:#757575;padding:0px 0px 0px 24px\">File</div><div style=\"padding:0px 0px 0px 24px\"><table style=\"width:100%;font-family:roboto,sans-serif;font-weight:400;font-size:14px\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><td><div>ViewGroup.java</div></td></tr></tbody></table></div></div><div style=\"padding:22px 24px 0px 0px;font-family:roboto,sans-serif;font-weight:400\"><div style=\"font-size:14px;line-height:20px;font-family:roboto,sans-serif;font-weight:400;color:#757575;padding:0px 0px 0px 24px\">Exception message</div><div style=\"padding:0px 0px 0px 24px\"><table style=\"width:100%;font-family:roboto,sans-serif;font-weight:400;font-size:14px\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><td><div style=\"font-size:14px;line-height:22px;font-family:roboto,sans-serif;font-weight:400;color:#212121;padding:2px 0px 0px 0px;font-family:monospace\">java.lang.<wbr>IllegalArgumentException: parameter must be a descendant of this view\n" +
                            "at android.view.ViewGroup.<wbr>offsetRectBetweenParentAndChil<wbr>d(ViewGroup.java:5334)\n" +
                            "</div></td></tr></tbody></table></div></div><div style=\"padding-left:20px;font-size:14px;line-height:16px;padding-top:12px;padding-bottom:34px;padding-top:34px;text-align:right;font-family:roboto,sans-serif;font-weight:500\"><a href=\"https://console.firebase.google.com/project/anona-705d7/monitoring/app/android:com.cybaze.goodbuy/cluster/7846c195?utm_source=alerts&amp;utm_medium=email&amp;utm_campaign=crash&amp;utm_content=cluster\" style=\"text-decoration:none;margin-right:24px;padding:12px 12px 12px 12px;border:0;color:#039be5;background-color:#ffffff;font-family:roboto,sans-serif;font-weight:500;border-radius:2px\" target=\"_blank\" rel=\"noreferrer\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=https://console.firebase.google.com/project/anona-705d7/monitoring/app/android:com.cybaze.goodbuy/cluster/7846c195?utm_source%3Dalerts%26utm_medium%3Demail%26utm_campaign%3Dcrash%26utm_content%3Dcluster&amp;source=gmail&amp;ust=1504955366065000&amp;usg=AFQjCNHDa_-uwZO9NB_x2q7mwXfkTP2EYw\">VIEW DETAILS</a></div></div><hr style=\"display:block;border:0;border-top:1px solid #e0e0e0;margin:0px 24px 0px 24px\"><div style=\"background-color:#ffffff;margin:0px 0px 0px 0px;font-size:14px;font-family:roboto,sans-serif;font-weight:400\"><div style=\"padding:22px 24px 0px 0px;font-family:roboto,sans-serif;font-weight:400\"><div style=\"font-size:14px;line-height:20px;font-family:roboto,sans-serif;font-weight:400;color:#757575;padding:0px 0px 0px 24px\">Fatal error</div><div style=\"padding:0px 0px 0px 24px\"><table style=\"width:100%;font-family:roboto,sans-serif;font-weight:400;font-size:14px\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><td><div>java.lang.<wbr>NumberFormatException</div></td></tr></tbody></table></div></div><div style=\"padding:22px 24px 0px 0px;font-family:roboto,sans-serif;font-weight:400\"><div style=\"font-size:14px;line-height:20px;font-family:roboto,sans-serif;font-weight:400;color:#757575;padding:0px 0px 0px 24px\">First seen version</div><div style=\"padding:0px 0px 0px 24px\"><table style=\"width:100%;font-family:roboto,sans-serif;font-weight:400;font-size:14px\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><td><div><a style=\"color:#212121\" rel=\"noreferrer\">1</a></div></td></tr></tbody></table></div></div><div style=\"padding:22px 24px 0px 0px;font-family:roboto,sans-serif;font-weight:400\"><div style=\"font-size:14px;line-height:20px;font-family:roboto,sans-serif;font-weight:400;color:#757575;padding:0px 0px 0px 24px\">File</div><div style=\"padding:0px 0px 0px 24px\"><table style=\"width:100%;font-family:roboto,sans-serif;font-weight:400;font-size:14px\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><td><div>Integer.java</div></td></tr></tbody></table></div></div><div style=\"padding:22px 24px 0px 0px;font-family:roboto,sans-serif;font-weight:400\"><div style=\"font-size:14px;line-height:20px;font-family:roboto,sans-serif;font-weight:400;color:#757575;padding:0px 0px 0px 24px\">Exception message</div><div style=\"padding:0px 0px 0px 24px\"><table style=\"width:100%;font-family:roboto,sans-serif;font-weight:400;font-size:14px\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><td><div style=\"font-size:14px;line-height:22px;font-family:roboto,sans-serif;font-weight:400;color:#212121;padding:2px 0px 0px 0px;font-family:monospace\">java.lang.<wbr>NumberFormatException: Invalid int: \"50.00\"\n" +
                            "at java.lang.Integer.invalidInt(<wbr>Integer.java:138)\n" +
                            "</div></td></tr></tbody></table></div></div><div style=\"padding-left:20px;font-size:14px;line-height:16px;padding-top:12px;padding-bottom:34px;padding-top:34px;text-align:right;font-family:roboto,sans-serif;font-weight:500\"><a href=\"https://console.firebase.google.com/project/anona-705d7/monitoring/app/android:com.cybaze.goodbuy/cluster/f5c12b28?utm_source=alerts&amp;utm_medium=email&amp;utm_campaign=crash&amp;utm_content=cluster\" style=\"text-decoration:none;margin-right:24px;padding:12px 12px 12px 12px;border:0;color:#039be5;background-color:#ffffff;font-family:roboto,sans-serif;font-weight:500;border-radius:2px\" target=\"_blank\" rel=\"noreferrer\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=https://console.firebase.google.com/project/anona-705d7/monitoring/app/android:com.cybaze.goodbuy/cluster/f5c12b28?utm_source%3Dalerts%26utm_medium%3Demail%26utm_campaign%3Dcrash%26utm_content%3Dcluster&amp;source=gmail&amp;ust=1504955366065000&amp;usg=AFQjCNE4_n3EsBQuVjxiSrjp4Yisb3ceig\">VIEW DETAILS</a></div></div><hr style=\"display:block;border:0;border-top:1px solid #e0e0e0;margin:0px 24px 0px 24px\"><div style=\"background-color:#ffffff;margin:0px 0px 0px 0px;font-size:14px;font-family:roboto,sans-serif;font-weight:400\"><div style=\"padding:22px 24px 0px 0px;font-family:roboto,sans-serif;font-weight:400\"><div style=\"font-size:14px;line-height:20px;font-family:roboto,sans-serif;font-weight:400;color:#757575;padding:0px 0px 0px 24px\">Fatal error</div><div style=\"padding:0px 0px 0px 24px\"><table style=\"width:100%;font-family:roboto,sans-serif;font-weight:400;font-size:14px\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><td><div>java.util.<wbr>MissingFormatArgumentException</div></td></tr></tbody></table></div></div><div style=\"padding:22px 24px 0px 0px;font-family:roboto,sans-serif;font-weight:400\"><div style=\"font-size:14px;line-height:20px;font-family:roboto,sans-serif;font-weight:400;color:#757575;padding:0px 0px 0px 24px\">First seen version</div><div style=\"padding:0px 0px 0px 24px\"><table style=\"width:100%;font-family:roboto,sans-serif;font-weight:400;font-size:14px\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><td><div><a style=\"color:#212121\" rel=\"noreferrer\">1</a></div></td></tr></tbody></table></div></div><div style=\"padding:22px 24px 0px 0px;font-family:roboto,sans-serif;font-weight:400\"><div style=\"font-size:14px;line-height:20px;font-family:roboto,sans-serif;font-weight:400;color:#757575;padding:0px 0px 0px 24px\">File</div><div style=\"padding:0px 0px 0px 24px\"><table style=\"width:100%;font-family:roboto,sans-serif;font-weight:400;font-size:14px\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><td><div>Formatter.java</div></td></tr></tbody></table></div></div><div style=\"padding:22px 24px 0px 0px;font-family:roboto,sans-serif;font-weight:400\"><div style=\"font-size:14px;line-height:20px;font-family:roboto,sans-serif;font-weight:400;color:#757575;padding:0px 0px 0px 24px\">Exception message</div><div style=\"padding:0px 0px 0px 24px\"><table style=\"width:100%;font-family:roboto,sans-serif;font-weight:400;font-size:14px\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><td><div style=\"font-size:14px;line-height:22px;font-family:roboto,sans-serif;font-weight:400;color:#212121;padding:2px 0px 0px 0px;font-family:monospace\">java.util.<wbr>MissingFormatArgumentException<wbr>: Format specifier: d\n" +
                            "at java.util.Formatter.<wbr>getArgument(Formatter.java:<wbr>1111)\n" +
                            "</div></td></tr></tbody></table></div></div><div style=\"padding-left:20px;font-size:14px;line-height:16px;padding-top:12px;padding-bottom:34px;padding-top:34px;text-align:right;font-family:roboto,sans-serif;font-weight:500\"><a href=\"https://console.firebase.google.com/project/anona-705d7/monitoring/app/android:com.cybaze.goodbuy/cluster/b25cafd0?utm_source=alerts&amp;utm_medium=email&amp;utm_campaign=crash&amp;utm_content=cluster\" style=\"text-decoration:none;margin-right:24px;padding:12px 12px 12px 12px;border:0;color:#039be5;background-color:#ffffff;font-family:roboto,sans-serif;font-weight:500;border-radius:2px\" target=\"_blank\" rel=\"noreferrer\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=https://console.firebase.google.com/project/anona-705d7/monitoring/app/android:com.cybaze.goodbuy/cluster/b25cafd0?utm_source%3Dalerts%26utm_medium%3Demail%26utm_campaign%3Dcrash%26utm_content%3Dcluster&amp;source=gmail&amp;ust=1504955366065000&amp;usg=AFQjCNFDSG0oYuxZd0avvWO2sH1QMxKr0g\">VIEW DETAILS</a></div></div><hr style=\"display:block;border:0;border-top:1px solid #e0e0e0;margin:0px 24px 0px 24px\"><div style=\"padding-left:20px;font-size:14px;line-height:16px;padding-top:12px;padding-bottom:34px;padding-top:34px;text-align:right;font-family:roboto,sans-serif;font-weight:500\"><a href=\"https://console.firebase.google.com/project/anona-705d7/monitoring/app/android:com.cybaze.goodbuy/crash?utm_source=alerts&amp;utm_medium=email&amp;utm_campaign=crash&amp;utm_content=dashboard\" style=\"text-decoration:none;margin-right:24px;padding:12px 12px 12px 12px;border:0;background-color:#039be5;color:#ffffff;font-family:roboto,sans-serif;font-weight:500;border-radius:2px\" target=\"_blank\" rel=\"noreferrer\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=https://console.firebase.google.com/project/anona-705d7/monitoring/app/android:com.cybaze.goodbuy/crash?utm_source%3Dalerts%26utm_medium%3Demail%26utm_campaign%3Dcrash%26utm_content%3Ddashboard&amp;source=gmail&amp;ust=1504955366065000&amp;usg=AFQjCNG1nAcXO4depFtPhaCcHlfG_Zyk4Q\">VIEW DASHBOARD</a></div></div><div style=\"background-color:#eceff1;padding:24px 36px 24px 36px;text-align:center;font-size:12px;line-height:16px;font-family:roboto,sans-serif;font-weight:400\"><div><a href=\"https://console.firebase.google.com/subscriptions/project/anona-705d7?utm_source=alerts&amp;utm_medium=email&amp;utm_campaign=crash&amp;utm_content=settings\" style=\"text-decoration:none;color:#039be5\" target=\"_blank\" rel=\"noreferrer\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=https://console.firebase.google.com/subscriptions/project/anona-705d7?utm_source%3Dalerts%26utm_medium%3Demail%26utm_campaign%3Dcrash%26utm_content%3Dsettings&amp;source=gmail&amp;ust=1504955366065000&amp;usg=AFQjCNF9Wqyow4jvp9IRXXynCwb2Db2fng\">ALERT SUBSCRIPTIONS</a></div><div style=\"padding-top:12px;color:#6c6d6e\">You are subscribed to Crash Reporting alerts for new issues in this app.</div></div><div style=\"background-color:#78909c;padding:23px 24px 23px 24px;min-height:42px\"><table style=\"width:100%;font-family:roboto,sans-serif;font-weight:400\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><td><img src=\"https://ci5.googleusercontent.com/proxy/sYBl6TSUS9lg4RlRCL0PLgu7-LmbjapWKSvDmmax59x-L1iKPoCv215zRMtoiHHXYqzGOh2FUwuRVlihk9NPaH1IEdNB7ACKXgJPZXUNz6f_7WbAzuL-eY-MCl0-mBGxrAWv_RJ4WjNUIY1fw0w=s0-d-e1-ft#https://www.gstatic.com/images/branding/googlelogo/2x/googlelogo_light_color_74x24dp.png\" width=\"74\" height=\"24\"></td><td style=\"text-align:right;padding:0\"><div style=\"line-height:14px;font-size:10px;color:#d6dde1\">Google Inc.<br><a href=\"https://maps.google.com/?q=1600+Amphitheatre+Pkwy+Mountain+View,+CA,+94043+USA&amp;entry=gmail&amp;source=g\">1600 Amphitheatre Pkwy</a><br><a href=\"https://maps.google.com/?q=1600+Amphitheatre+Pkwy+Mountain+View,+CA,+94043+USA&amp;entry=gmail&amp;source=g\">Mountain View, CA, 94043 USA</a><br></div></td></tr></tbody></table></div></div></div>", "text/html; charset=utf-8");

                    Transport.send(mimeMessage);
                    System.out.println("Mail sent :" + message);
                } catch (MessagingException e) {
                    e.printStackTrace();
                    System.out.println("Failed to send mail");
                }

            }
        }).start();
    }

}
