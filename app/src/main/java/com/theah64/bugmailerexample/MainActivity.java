package com.theah64.bugmailerexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.theah64.bugmailer.core.BugMailer;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Person person = new Person("theapache64", "20");

        try {
            JSONObject jo = new JSONObject("custom-json");
        } catch (JSONException e) {
            e.printStackTrace();

            //Manually reporting with custom object
            BugMailer.report(e, person);
        }

    }

}
