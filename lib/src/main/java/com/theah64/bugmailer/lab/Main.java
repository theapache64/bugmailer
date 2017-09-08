package com.theah64.bugmailer.lab;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Main {

    public static void main(String[] args) {
        // write your code here

        try {
            throw new IllegalArgumentException("Example exception");
        } catch (Exception e) {

            final String stackTrace = getStackTrace(e);
            final String[] stackLines = stackTrace.split("\n");
            final String primaryStackLine = String.format("<b>%s %s </b>", stackLines[0], stackLines[1].trim());


            final String report = new BugMailer.ReportGenerator.Builder("GoodBuy", "com.cybaze.goodbuy")
                    .addNode(new BugMailer.ReportGenerator.Builder.Node("Key 1", "Value 1"))
                    .addNode(new BugMailer.ReportGenerator.Builder.Node("Key 1", "Value 1"))
                    .addNode(new BugMailer.ReportGenerator.Builder.Node("Key 2", "Value 2"))
                    .addNode(new BugMailer.ReportGenerator.Builder.Node("Key 3", "Value 3"))
                    .addNode(new BugMailer.ReportGenerator.Builder.Node("Key 4", "Value 4"))
                    .addNode(new BugMailer.ReportGenerator.Builder.Node("Key 5", "Value 5"))
                    .addNode(new BugMailer.ReportGenerator.Builder.Node("Primary stacktrack", primaryStackLine))
                    .addNode(new BugMailer.ReportGenerator.Builder.Node("Stacktrace", stackTrace))
                    .build();


            System.out.println(report);
        }


    }

    private static String getStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

    static class BugMailer {
        static class ReportGenerator {
            static class Builder {

                private static final String HEADER = "<html><head></head><body><div class=\"msg\"> <div style=\"font-size:16px;border:1px solid #e0e0e0;max-width:800px;font-family:roboto,sans-serif;font-weight:400\"> <div style=\"padding:20px 0px 20px 0px;text-align:center;background-color:#ffffff\"><img src=\"https://i.stack.imgur.com/U2JVG.png\" style=\"width:145px;min-height:40px\"></div> <div style=\"padding:24px 24px 24px 24px;background-color:#a4c639;font-family:roboto,sans-serif;font-weight:400\"> <table style=\"width:100%;font-family:roboto,sans-serif;font-weight:400\" cellpadding=\"0\" cellspacing=\"0\"> <tbody> <tr> <td> <div style=\"font-size:14px;line-height:16px;color:#fff\">PROJECTNAME</div> <div style=\"font-size:20px;line-height:24px;color:#ffffff\">New issue found in PACKAGENAME</div> </td> <td> <div style=\"text-align:right\"><img src=\"https://ci6.googleusercontent.com/proxy/Cvj52vFVxSfNWI4XcQgZJvyDSATy77fAyZvSRAMKVOONvK0uQn8NBMx1LyeaDGUkZ7c8xyQ-UiwGXlRWUvEpiia0Euc3wL-kBn8aWPjlGZmGgxBZpTyJRZgJuD9RVkPun0e9m8odWw=s0-d-e1-ft#https://www.gstatic.com/images/icons/material/system/2x/bug_report_white_24dp.png\" style=\"min-height:24px;width:24px\"></div> </td> </tr> </tbody> </table> </div> <div style=\"font-family:roboto,sans-serif;font-weight:400\"> <hr style=\"display:block;border:0;border-top:1px solid #e0e0e0;margin:0px 24px 0px 24px\">";
                private static final String FOOTER = "<div style=\"background-color:#ffffff;margin:0px 0px 0px 0px;font-size:14px;font-family:roboto,sans-serif;font-weight:400\"> <div style=\"padding-left:20px;font-size:14px;line-height:16px;padding-top:12px;padding-bottom:34px;padding-top:34px;text-align:right;font-family:roboto,sans-serif;font-weight:500\"> <a href=\"http://stackoverflow.com\" style=\"text-decoration:none;margin-right:24px;padding:12px 12px 12px 12px;border:0;color:#a4c639;background-color:#ffffff;font-family:roboto,sans-serif;font-weight:500;border-radius:2px\" target=\"_blank\" rel=\"noreferrer\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=https://console.firebase.google.com/project/anona-705d7/monitoring/app/android:com.cybaze.goodbuy/cluster/7846c195?utm_source%3Dalerts%26utm_medium%3Demail%26utm_campaign%3Dcrash%26utm_content%3Dcluster&amp;source=gmail&amp;ust=1504955366065000&amp;usg=AFQjCNHDa_-uwZO9NB_x2q7mwXfkTP2EYw\">SEARCH IN STACKOVERFLOW</a></div> </div> <hr style=\"display:block;border:0;border-top:1px solid #e0e0e0;margin:0px 24px 0px 24px\"> <hr style=\"display:block;border:0;border-top:1px solid #e0e0e0;margin:0px 24px 0px 24px\"> <hr style=\"display:block;border:0;border-top:1px solid #e0e0e0;margin:0px 24px 0px 24px\"> </div> <div style=\"background-color:#343434;padding:23px 24px 23px 24px;min-height:42px\"> <table style=\"width:100%;font-family:roboto,sans-serif;font-weight:400\" cellpadding=\"0\" cellspacing=\"0\"> <tbody> <tr> <td style=\"text-align:right;padding:0\"> <div style=\"line-height:14px;font-size:10px;color:#fff\">BugMailer<br><a style=\"color:#fff\" href=\"mailto:theapache64@gmail.com\"> theapache64@gmail.com </a><br><a style=\"color:#fff\" href=\"https://github.com/theapache64/BugMailer\">A Github opensource project</a><br></div> </td> </tr> </tbody> </table> </div> </div> </div> </body></html>";
                private static final String KEY_PROJECT_NAME = "PROJECTNAME";
                private static final String KEY_PACKAGE_NAME = "PACKAGENAME";

                private final StringBuilder report;

                public Builder(final String projectName, String packageName) {

                    String f1 = HEADER.replaceAll(KEY_PROJECT_NAME, projectName);
                    f1 = f1.replaceAll(KEY_PACKAGE_NAME, packageName);

                    report = new StringBuilder(f1);
                }

                public String build() {
                    report.append(FOOTER);
                    return report.toString();
                }

                public Builder addNode(Node node) {
                    report.append(node);
                    return this;
                }

                static class Node {

                    private static final String KEY_HEADER = "<div style=\"padding:22px 24px 0px 0px;font-family:roboto,sans-serif;font-weight:400\"> <div style=\"font-size:14px;line-height:20px;font-family:roboto,sans-serif;font-weight:400;color:#757575;padding:0px 0px 0px 24px\">";
                    private static final String KEY_FOOTER = "</div> <div style=\"padding:0px 0px 0px 24px\"> <table style=\"width:100%;font-family:roboto,sans-serif;font-weight:400;font-size:14px\" cellpadding=\"0\" cellspacing=\"0\"> <tbody> <tr> <td> <div>";
                    private static final String VALUE_FOOTER = "</div> </td> </tr> </tbody> </table> </div> </div>";

                    private final StringBuilder report;

                    public Node(final String key, final String value) {
                        report = new StringBuilder(KEY_HEADER)
                                .append(key)
                                .append(KEY_FOOTER)
                                .append(value)
                                .append(VALUE_FOOTER);
                    }

                    @Override
                    public String toString() {
                        return report.toString();
                    }
                }

            }
        }
    }
}
