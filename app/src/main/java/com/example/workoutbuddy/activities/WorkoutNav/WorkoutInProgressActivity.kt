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
        val descriptions = arrayOf(
            "Lie down on back, lift your upper body and return back to starting position. Repeat.",
            "From sit-up position, straighten out and raise legs perpendicular to the floor. Repeatedly reach for toes.",
            "Engage core by sitting in v-shaped position, keeping legs off the floor. Alternate twisting motion on both sides.",
            "Lying on back, imitate pedaling on a bike while keeping chin up and core engaged.",
            "Lie down on back, keep legs together and straightened, raise them up to engage lower core. Lower legs without touching the floor."
        )

        val exName = findViewById<TextView>(R.id.curExNameTV)
        val wDescr = findViewById<TextView>(R.id.workoutdesc)
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
            wDescr.text = descriptions[x-1]
        }


        nextBtn.setOnClickListener {
            backBtn.isEnabled = true

            if(progressBar.progress == progressBar.max) {
                val i = Intent(this, EndWorkoutActivity::class.java)
                i.putExtra("completed", true)
                //i.putExtra("type", type)
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
            wDescr.text = descriptions[x-1]

        }

        endWorkoutTV.setOnClickListener{
            val i = Intent(this, EndWorkoutActivity::class.java)
            i.putExtra("completed", false)
            startActivity(i)
            finish()
        }
    }

    override fun onResume() {
        super.onResume()

    }
}