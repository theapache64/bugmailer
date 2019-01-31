package com.theah64.bugmailerexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast

import com.theah64.bugmailer.core.BugMailer

import org.json.JSONException

import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.bCrashApp).setOnClickListener {
            val person = Person("theapache64", "20")

            try {
                throw IOException("Test exception")
            } catch (e: IOException) {
                e.printStackTrace()

                //Manually reporting with custom object
                BugMailer.report(e, person)
                Toast.makeText(this@MainActivity, "Crash thrown!: " + e.message, Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<View>(R.id.bThrowJSONException).setOnClickListener {
            try {
                throw JSONException("Sample json exception")
            } catch (e: JSONException) {
                e.printStackTrace()

                BugMailer.report(e)
                Toast.makeText(this@MainActivity, "JSONCrash thrown!: " + e.message, Toast.LENGTH_SHORT).show()
            }
        }

    }

}
