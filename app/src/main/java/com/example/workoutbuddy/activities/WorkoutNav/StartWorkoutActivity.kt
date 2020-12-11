package com.example.workoutbuddy.activities.WorkoutNav

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutbuddy.Data.Routine
import com.example.workoutbuddy.Data.RoutineDao
import com.example.workoutbuddy.ExerciseAdapter
import com.example.workoutbuddy.R
import com.example.workoutbuddy.RoutineAdapter
import com.example.workoutbuddy.ViewModels.RoutineViewModel
import com.example.workoutbuddy.WorkoutAdapter2
import kotlinx.android.synthetic.main.activity_start_workout.*
import kotlinx.android.synthetic.main.fragment_exercises.*

private lateinit var routineViewModel: RoutineViewModel

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
        val name = bundle?.getString("wName", "Title") ?: "NAME?"
        val category = bundle?.getString("wCat","Title") ?: "CAT?"
        val descr = bundle?.getString("wDesc", "Title") ?: "DESC?"
        val workoutID = bundle?.getInt("wID") ?: 69
        val routineRecyclerView = findViewById<RecyclerView>(R.id.routineRV)
        var mRoutines: List<Routine> = listOf()

        //Display Workout info that was clicked
        wNameTV.text = name
        wDescriptionTV.text = descr


        // Display Recycler View of Routines
        val adapter = RoutineAdapter(this)
        routineRecyclerView.adapter = adapter
        routineRecyclerView.layoutManager = LinearLayoutManager(this)
        routineRecyclerView.setHasFixedSize(true)

        // set Routines from database
        routineViewModel = ViewModelProvider(this).get(RoutineViewModel::class.java)
        routineViewModel.allRoutines.observe(this, Observer { routines ->
            routines?.let { (routineRecyclerView.adapter as RoutineAdapter).setFilteredRoutines(routines, name) }
        })


        val routinesToPass = (routineRecyclerView.adapter as RoutineAdapter).getRoutines()
        val rNames = arrayListOf<String>()
        val rTypes = arrayListOf<String>()
        val rDescriptions = arrayListOf<String>()
        val rSets = arrayListOf<Int>()
        val rReps= arrayListOf<Int>()
        val rSetsQ = arrayListOf<String>()

        for(routine in routinesToPass) {
            rNames.add(routine.name)
            rTypes.add(routine.type)
            rDescriptions.add(routine.description)
            routine.sets?.let { rSets.add(it) }
            routine.reps?.let { rReps.add(it) }
            routine.setQuantifier?.let { rSetsQ.add(it) }
        }




        // Back button to main activity
        backBtn.setOnClickListener {
            onBackPressed()
        }


        startWorkoutBtn.setOnClickListener {
            val i2 = Intent(this, WorkoutInProgressActivity::class.java)
            i2.putExtra("wName", name)
            i2.putExtra("wCat", category)
            i2.putExtra("wID", workoutID)

            i2.putStringArrayListExtra("rNames", rNames)
            i2.putStringArrayListExtra("rTypes", rTypes)
            i2.putStringArrayListExtra("rDesc", rDescriptions)
            i2.putIntegerArrayListExtra("rSets", rSets)
            i2.putIntegerArrayListExtra("rReps", rReps)
            i2.putStringArrayListExtra("rSetsQ", rSetsQ)
            startActivity(i2)
            finish()
        }
    }

    override fun onResume() {
        super.onResume()

    }
}