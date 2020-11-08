package com.example.workoutbuddy.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Transition
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutbuddy.*
import kotlinx.android.synthetic.main.activity_new_workout.*
import kotlinx.android.synthetic.main.activity_new_workout2.*
import kotlinx.android.synthetic.main.exercise_item_options.*

private lateinit var exerciseViewModel: ExerciseViewModel

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
            val fadeOut = AnimationUtils.loadAnimation(this, R.anim.fragment_fade_exit)
            val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fragment_fade_enter)
            if (recyclerView.isVisible) {
                recyclerView.isVisible = false
                recyclerView.startAnimation(fadeOut)
            } else {
                recyclerView.isVisible = true
                recyclerView.startAnimation(fadeIn)
            }




            //finish()
        }
    }


    fun compileWorkout(name: String, category: String, description: String, exercises: List<ExerciseItem>) {
        val workoutItem = WorkoutItem(R.drawable.ic_baseline_fitness_center_24, name, category, description, exercises)


    }

}