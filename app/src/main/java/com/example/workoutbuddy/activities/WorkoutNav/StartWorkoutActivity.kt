package com.example.workoutbuddy.activities.WorkoutNav

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.workoutbuddy.R

class StartWorkoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_workout)

        // initialize all widgets
//      val imageView: Imag = findViewById<ImageView>(R.id.dadfsdfgsdfdsgsg)
        val wNameTV = findViewById<TextView>(R.id.wNameTV)
        val wDescriptionTV = findViewById<TextView>(R.id.textView4)
//      val exerciseListView

        val backBtn = findViewById<Button>(R.id.startActBtn)
        val startWorkoutBtn = findViewById<Button>(R.id.startWorkoutBtn)

        //get data using intent from Workout Fragment
        //ITERATION 3: extract intent data and store in variables
        val bundle = intent.extras
        val name = bundle?.getString("wName", "Title") ?: ""
        val descr = bundle?.getString("wDesc", "Title") ?: ""
        // val exercises = bundle?.getStringArray()

        //Display Workout info that was clicked
        wNameTV.text = name
        wDescriptionTV.text = descr

        //Display list of exercises


        // Back button to main activity
        backBtn.setOnClickListener {
            onBackPressed()
        }


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