package com.example.workoutbuddy.activities.WorkoutNav

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.workoutbuddy.R

class WorkoutInProgressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_in_progress)

        val exercises = arrayOf("Crunches", "Toe Touches","Russian Twists", "Bicycle Crunches", "Leg Raises")

        val exName = findViewById<TextView>(R.id.curExNameTV)
        val backBtn = findViewById<Button>(R.id.prevExBtn)
        val nextBtn = findViewById<Button>(R.id.nextExBtn)
        val endWorkoutTV = findViewById<TextView>(R.id.endWorkoutTV)
        val wProgressTV = findViewById<TextView>(R.id.textView3)
        var x = 1
        val end = 5

        val progressBar = findViewById<ProgressBar>(R.id.progressBar2)

        backBtn.isEnabled = false

        backBtn.setOnClickListener {
            if (progressBar.progress > 20) {
                progressBar.progress -= 20

                x--
                wProgressTV.text = "Exercise $x of $end"



            }

            if (progressBar.progress == 20) {
                backBtn.isEnabled = false
            }

            if(progressBar.progress != progressBar.max) {
                endWorkoutTV.isVisible = true
                nextBtn.text = "Next Exercise"
            }
            exName.text = exercises[x-1]
        }


        nextBtn.setOnClickListener {
            backBtn.isEnabled = true

            if(progressBar.progress == progressBar.max) {
                val i = Intent(this, EndWorkoutActivity::class.java)
                startActivity(i)
                finish()
            }

            if (progressBar.progress < progressBar.max) {
                progressBar.progress += 20
                x++
                wProgressTV.text = "Exercise $x of $end"
            }
            if(progressBar.progress == progressBar.max) {
                endWorkoutTV.isVisible = false
                nextBtn.text = "Finish"
            }
            exName.text = exercises[x-1]

        }

        endWorkoutTV.setOnClickListener{
            val i = Intent(this, EndWorkoutActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    override fun onResume() {
        super.onResume()

    }
}