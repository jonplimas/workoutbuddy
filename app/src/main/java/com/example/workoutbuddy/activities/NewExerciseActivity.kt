package com.example.workoutbuddy.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.workoutbuddy.ExerciseAdapter
import com.example.workoutbuddy.R

class NewExerciseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_exercise)

        val mStartActBtn = findViewById<Button>(R.id.startActBtn)

        //handle back button click
        mStartActBtn.setOnClickListener {
            //Destroy Actviity
            finish()
        }
    }

}