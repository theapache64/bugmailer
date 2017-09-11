package com.theah64.bugmailerexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.theah64.bugmailer.core.BugMailer;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Person person = new Person("Anooj", "20");

        BugMailer.report(new JSONException("Sample json exception"), person);
    }

}
