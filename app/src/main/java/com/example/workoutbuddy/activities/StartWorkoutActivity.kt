package com.example.workoutbuddy.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import com.example.workoutbuddy.R

class StartWorkoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_workout)

        // initialize all widgets
//      val imageView: Imag = findViewById<ImageView>(R.id.dadfsdfgsdfdsgsg)
//      val wNameTV
//      val wDescriptionTV
//      val exerciseListView

        val startWorkoutBtn = findViewById<Button>(R.id.startWorkoutBtn)

        //get data using intent from Workout Fragment
        val i = intent

        startWorkoutBtn.setOnClickListener {
            val i2 = Intent(this, WorkoutInProgress::class.java)
            startActivity(i2)
            // finish()
        }
    }

    override fun onResume() {
        super.onResume()


    }
}