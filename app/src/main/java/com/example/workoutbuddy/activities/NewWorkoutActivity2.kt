package com.example.workoutbuddy.activities

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutbuddy.*
import com.example.workoutbuddy.Data.ExerciseItem
import com.example.workoutbuddy.Data.Routine
import com.example.workoutbuddy.ViewModels.ExerciseViewModel
import com.example.workoutbuddy.Data.WorkoutItem
import com.example.workoutbuddy.ViewModels.RoutineViewModel
import com.example.workoutbuddy.ViewModels.WorkoutViewModel
import kotlinx.android.synthetic.main.activity_new_workout2.*


private lateinit var exerciseViewModel: ExerciseViewModel
private lateinit var routineViewModel: RoutineViewModel

class NewWorkoutActivity2 : AppCompatActivity() {

    private var listOfReps = arrayOf("reps", "secs")


    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_workout2)


        val bundle = intent.extras
        val name = bundle?.getString("wName", "Title") ?: ""
        val type = bundle?.getString("wType", "Title") ?: ""
        val descr = bundle?.getString("wDescr", "Title") ?: ""
        val id = bundle?.getInt("wID")

        Toast.makeText(this, "NAME: $name   TYPE: $type    DESCR: $descr", Toast.LENGTH_SHORT).show()


        val recyclerView = findViewById<RecyclerView>(R.id.addWorkoutRecyclerView)
        val adapter = WorkoutAdapter2(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)


        //Filter Selection of Exercises based on Workout category chosen
        exerciseViewModel = ViewModelProvider(this).get(ExerciseViewModel::class.java)
        if(type == "Core") {
            exerciseViewModel.coreExercises.observe(this , Observer { exercises ->
                exercises?.let { adapter.setExercises(it) }
            })
        } else if(type == "Upper Body") {
            exerciseViewModel.upperExercises.observe(this , Observer { exercises ->
                exercises?.let { adapter.setExercises(it) }
            })
        } else if(type == "Lower Body") {
            exerciseViewModel.lowerExercises.observe(this , Observer { exercises ->
                exercises?.let { adapter.setExercises(it) }
            })
        } else {    // "Full Body"
            exerciseViewModel.allExercises.observe(this , Observer { exercises ->
                exercises?.let { adapter.setExercises(it) }
            })
        }

        // Back Button to return to first new workout page
        startActBtn.setOnClickListener {
            onBackPressed()
        }

        // COMPILING THE ROUTINE
        createNewWorkoutButton.setOnClickListener {

//            val fadeOut = AnimationUtils.loadAnimation(this, R.anim.fragment_fade_exit)
//            val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fragment_fade_enter)
//            if (recyclerView.isVisible) {
//                recyclerView.isVisible = false
//                recyclerView.startAnimation(fadeOut)
//            } else {
//                recyclerView.isVisible = true
//                recyclerView.startAnimation(fadeIn)
//            }

            val xNames: MutableList<String> = mutableListOf()
            val xTypes: MutableList<String> = mutableListOf()
            val xDescripts: MutableList<String> = mutableListOf()
            val xReps: MutableList<String> = mutableListOf()
            val xSets: MutableList<String> = mutableListOf()
            val xSetsQ: MutableList<String> = mutableListOf()

            for(i in 0 until adapter.itemCount) {
                //Store data of all checked items into arrays
                if(adapter.checkBoxStateArray[i]) {
                    val exercise = adapter.getExercise(i)
                    xNames += exercise.name
                    xTypes += exercise.type!!.toString()
                    xDescripts += exercise.description!!.toString()
                    xReps += exercise.reps!!.toString()
                    xSets += exercise.sets!!.toString()
                    xSetsQ += exercise.setQuantifier!!.toString()
                }
            }
            Toast.makeText(this, "1st EXERCISE: ${xNames[0]}, ${xSets[0]} Sets X ${xReps[0]} ${xSetsQ[0]}", Toast.LENGTH_LONG).show()

            lateinit var routine: Routine
            for(i in 0 until xNames.size) {
                // create routine
                routine = Routine(
                    exID = id!!.toInt(),
                    name = xNames[i],
                    type = xTypes[i],
                    description = xDescripts[i],
                    sets = xSets[i].toInt(),        // check if pulling correct
                    reps = xReps[i].toInt(),        //
                    setQuantifier = xSetsQ[i]
                )
                // insert into DB
                //routineViewModel = ViewModelProvider(this).get(RoutineViewModel::class.java)
                //routineViewModel.insertRoutine(routine)
            }

            // Package intent with data needed to make workout
            val reply = Intent()

            reply.putExtra("Name", name)
            reply.putExtra("Category", type)
            reply.putExtra("Descr", descr)
            reply.putExtra("xNames", xNames.toTypedArray())
            reply.putExtra("xTypes", xTypes.toTypedArray())
            reply.putExtra("xDescripts", xDescripts.toTypedArray())

            setResult(Activity.RESULT_OK, reply)
            finish()
        }
    }
}