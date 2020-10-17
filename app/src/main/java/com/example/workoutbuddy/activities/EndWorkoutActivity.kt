package com.example.workoutbuddy.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.workoutbuddy.R
import com.example.workoutbuddy.fragments.HomeFragment

class EndWorkoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_workout)

        val finishWorkoutButton = findViewById<Button>(R.id.finishWorkoutBtn)


        finishWorkoutButton.setOnClickListener {
            val i = Intent()
            setResult(Activity.RESULT_OK, i)
            finish()
        }

    }
}