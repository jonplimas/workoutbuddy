package com.example.workoutbuddy.activities.WorkoutNav

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutbuddy.Data.Routine
import com.example.workoutbuddy.Data.RoutineDao
import com.example.workoutbuddy.R
import com.example.workoutbuddy.ViewModels.RoutineViewModel
import com.example.workoutbuddy.WorkoutAdapter2

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
        var mRoutines: List<Routine> = listOf()

        routineViewModel = ViewModelProvider(this).get(RoutineViewModel::class.java)
        routineViewModel.allRoutines.observe(this, Observer { routines ->
            mRoutines = routines
        })

        val routines: MutableList<Routine> = mutableListOf()
        for(m in mRoutines){
            if(m.exID == workoutID){
                routines.add(m)
            }
        }

        //Display Workout info that was clicked
        wNameTV.text = name
        wDescriptionTV.text = descr

        //Display RecyclerView HERE


        // Back button to main activity
        backBtn.setOnClickListener {
            onBackPressed()
        }


        startWorkoutBtn.setOnClickListener {
            val i2 = Intent(this, WorkoutInProgressActivity::class.java)
            i2.putExtra("wName", name)
            i2.putExtra("wCat", category)
            i2.putExtra("wID", workoutID)
            startActivity(i2)
            finish()
        }
    }

    override fun onResume() {
        super.onResume()

    }
}