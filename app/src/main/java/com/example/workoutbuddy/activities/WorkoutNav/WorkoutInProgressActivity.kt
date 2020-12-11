package com.example.workoutbuddy.activities.WorkoutNav

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.workoutbuddy.Data.Routine
import com.example.workoutbuddy.R
import com.example.workoutbuddy.ViewModels.RoutineViewModel

private lateinit var routineViewModel: RoutineViewModel
private lateinit var mRoutines: MutableList<Routine>

class WorkoutInProgressActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_in_progress)


        //get extras from intent
        val bundle = intent.extras
        val name = bundle?.getString("wName", "Title") ?: "NAME?"
        val category = bundle?.getString("wCat","Title") ?: "CAT?"
        val workoutID = bundle?.getInt("wID", 1) ?: 69


        // hardcoded list of Exercises and their descriptions
        val exercises = arrayOf("Crunches", "Toe Touches","Russian Twists", "Bicycle Crunches", "Leg Raises")
        val descriptions = arrayOf(
            "Lie down on back, lift your upper body and return back to starting position. Repeat.",
            "From sit-up position, straighten out and raise legs perpendicular to the floor. Repeatedly reach for toes.",
            "Engage core by sitting in v-shaped position, keeping legs off the floor. Alternate twisting motion on both sides.",
            "Lying on back, imitate pedaling on a bike while keeping chin up and core engaged.",
            "Lie down on back, keep legs together and straightened, raise them up to engage lower core. Lower legs without touching the floor."
        )


        routineViewModel = ViewModelProvider(this).get(RoutineViewModel::class.java)
        routineViewModel.allRoutines.observe(this, Observer { routines ->
            for(routine in routines) {
                if(name== routine.exID){
                    mRoutines.add(routine)
                }
            }
        })

//        val myRoutines: MutableList<Routine> = mutableListOf()
//        for(routine in mRoutines) {
//            if(routine.exID == name) {
//                myRoutines.add(routine)
//            }
//        }

        Toast.makeText(this, "2nd Routine ${mRoutines[1].name}", Toast.LENGTH_SHORT).show()

        val exName = findViewById<TextView>(R.id.curExNameTV)
        val wDescr = findViewById<TextView>(R.id.workoutdesc)
        val backBtn = findViewById<Button>(R.id.prevExBtn)
        val nextBtn = findViewById<Button>(R.id.nextExBtn)
        val endWorkoutTV = findViewById<TextView>(R.id.endWorkoutTV)
        val wProgressTV = findViewById<TextView>(R.id.textView3)
        val repsSetsTV = findViewById<TextView>(R.id.textView11)
        var x = 1
        val end = mRoutines.size
        val incrementSize = 100 / end

        val progressBar = findViewById<ProgressBar>(R.id.progressBar2)

        backBtn.isEnabled = false

        backBtn.setOnClickListener {
            if (progressBar.progress > incrementSize) {
                progressBar.progress -= incrementSize
                x--
                wProgressTV.text = "Exercise $x of $end"

            }

            if (progressBar.progress == incrementSize) {
                backBtn.isEnabled = false
            }

            if(progressBar.progress != progressBar.max) {
                endWorkoutTV.isVisible = true
                nextBtn.text = "Next Exercise"
            }
            exName.text = mRoutines[x-1].name
            wDescr.text = mRoutines[x-1].description
            repsSetsTV.text = "${mRoutines[x-1].sets} Sets  X  ${mRoutines[x-1].reps} ${mRoutines[x-1].setQuantifier} "
        }


        nextBtn.setOnClickListener {
            backBtn.isEnabled = true

            if(progressBar.progress == progressBar.max) {
                val i = Intent(this, EndWorkoutActivity::class.java)
                i.putExtra("completed", true)
                i.putExtra("wName", name)
                i.putExtra("wCat", category)

                startActivity(i)
                finish()
            }

            if (progressBar.progress < progressBar.max) {
                progressBar.progress += incrementSize
                x++
                wProgressTV.text = "Exercise $x of $end"
            }
            if(progressBar.progress == progressBar.max) {
                endWorkoutTV.isVisible = false
                nextBtn.text = "Finish"
            }
            exName.text = mRoutines[x-1].name
            wDescr.text = mRoutines[x-1].name
            repsSetsTV.text = "${mRoutines[x-1].sets} Sets  X  ${mRoutines[x-1].reps} ${mRoutines[x-1].setQuantifier} "
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