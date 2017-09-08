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
 */
public class BugMailer {

    private static String gmailUsername, gmailPassword;
    private static String reportToEmail;


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
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sendMail(e.getMessage());
                }
            }).start();
            defaultUEH.uncaughtException(t, e);
        }
    }


    private static boolean sendMail(String message) {

        System.out.println("Sending email to " + reportToEmail);

        System.out.println("u:" + gmailUsername);
        System.out.println("p:" + gmailPassword);


        final Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(gmailUsername, gmailPassword);
            }
        });

        Message mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(new InternetAddress(gmailUsername));
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(reportToEmail));
            mimeMessage.setSubject("New user @ WSB");
            mimeMessage.setText(message);

            Transport.send(mimeMessage);
            System.out.println("Mail sent :" + message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        System.out.println("Failed to send mail");

        return false;
    }

}
