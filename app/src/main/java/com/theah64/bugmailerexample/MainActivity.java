package com.theah64.bugmailerexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.theah64.bugmailer.BugMailer;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BugMailer.sendMail("<b>Hello</b>");

        try {
            JSONObject jsonObject = new JSONObject("{}]");
            jsonObject.getString("hello");
        } catch (final Exception e) {
            Log.e("X", "Default erorr : " + e.getMessage());

        }
    }

}
