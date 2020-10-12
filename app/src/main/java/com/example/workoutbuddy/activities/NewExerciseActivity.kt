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
        //handle button click
        mStartActBtn.setOnClickListener {
            //start activity intent
            startActivity(Intent(this@NewExerciseActivity, ExerciseAdapter::class.java))
        }
    }

}