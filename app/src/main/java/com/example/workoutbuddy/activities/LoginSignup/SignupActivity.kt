package com.example.workoutbuddy.activities.LoginSignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.workoutbuddy.R

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val mStartActBtn = findViewById<Button>(R.id.startActBtn)

        // handle back button click
        mStartActBtn.setOnClickListener {
            // close out activity
            finish()
        }
    }
}