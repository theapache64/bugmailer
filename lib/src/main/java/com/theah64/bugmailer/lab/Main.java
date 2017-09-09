package com.theah64.bugmailer.lab;

import java.io.IOException;
import java.util.regex.Pattern;

public class Main {


    public static void main(String[] args) throws IOException {
        // write your code here
        String data = "Person{name='Shifar', age='20'}";
        System.out.println(Pattern.compile("\\{.+\\}").matcher(data).group(0));

    }


}
