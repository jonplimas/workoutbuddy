package com.example.workoutbuddy.activities.WorkoutNav

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
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
            val i2 = Intent(this, WorkoutInProgressActivity::class.java)
            startActivity(i2)
            finish()
        }
    }

    override fun onResume() {
        super.onResume()


    }
}