package com.theah64.bugmailerexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.theah64.bugmailer.core.BugMailer;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.bCrashApp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Person person = new Person("theapache64", "20");

                try {
                    throw new IOException("Test exception");
                } catch (IOException e) {
                    e.printStackTrace();

                    //Manually reporting with custom object
                    BugMailer.report(e, person);
                    Toast.makeText(MainActivity.this, "Crash thrown!: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        });

        findViewById(R.id.bThrowJSONException).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    throw new JSONException("Sample json exception");
                } catch (JSONException e) {
                    e.printStackTrace();

                    BugMailer.report(e);
                    Toast.makeText(MainActivity.this, "JSONCrash thrown!: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
