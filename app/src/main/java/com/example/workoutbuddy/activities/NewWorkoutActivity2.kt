package com.example.workoutbuddy.activities

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutbuddy.*
import com.example.workoutbuddy.Data.ExerciseItem
import com.example.workoutbuddy.ViewModels.ExerciseViewModel
import com.example.workoutbuddy.Data.WorkoutItem
import com.example.workoutbuddy.ViewModels.WorkoutViewModel
import kotlinx.android.synthetic.main.activity_new_workout2.*


private lateinit var exerciseViewModel: ExerciseViewModel
private lateinit var workoutViewModel: WorkoutViewModel

class NewWorkoutActivity2 : AppCompatActivity() {
    private var listOfItems = arrayOf("Full Body", "Upper Body", "Lower Body", "Core")
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_workout2)


        val bundle = intent.extras
        val name = bundle?.getString("wName", "Title") ?: ""
        val type = bundle?.getString("wType", "Title") ?: ""
        val descr = bundle?.getString("wDescr", "Title") ?: ""

        Toast.makeText(this, "$name   $type    $descr", Toast.LENGTH_SHORT).show()


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


        createNewWorkoutButton.setOnClickListener {
            //compile workout logic here


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
                //if item at index i is checked:
                //  add exercise to a list
                val exercise = adapter.getExercise(i)
                xNames += exercise.name
                xTypes += exercise.type!!.toString()
                xDescripts += exercise.description!!.toString()
                // xReps = EditTextInput.text
                // xSets = EditTextInput.text
                // xSetsQ = scroller.text

            }



            // Package intent with data needed to make workout
            val reply = Intent()
            reply.putExtra("Name", name)
            reply.putExtra("Category", type)
            reply.putExtra("Descr", descr)
            //reply.putExtra("xNames", xNames.toTypedArray())
            //reply.putExtra("xTypes", xTypes.toTypedArray())
            //reply.putExtra("xDescripts", xDescripts.toTypedArray())

            setResult(Activity.RESULT_OK, reply)
            finish()
        }
    }


    fun compileWorkout(name: String, category: String, description: String, exercises: List<ExerciseItem>) {
        val workoutItem = WorkoutItem(
            R.drawable.ic_baseline_fitness_center_24,
            99,99, name,
            category,
            description
        )


    }

}