package com.theah64.bugmailer.lab;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {
        // write your code here
        String DATA = "java.lang.RuntimeException: ";
        DATA = DATA.replaceAll("\\{.+\\}", "");
        final String[] lines = DATA.split(":");
        final StringBuilder queryBuilder = new StringBuilder();
        for (int i = 0; i < (lines.length > 3 ? 3 : lines.length); i++) {
            queryBuilder.append(lines[i]);
        }

        System.out.println(queryBuilder);
    }


}
