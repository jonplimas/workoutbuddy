package com.example.workoutbuddy.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutbuddy.*
import com.example.workoutbuddy.activities.MainActivity
import com.example.workoutbuddy.activities.NewExerciseActivity
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_home.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

// ITERATION 1: implemented a hardcoded list of 10 Exercise Items to simulate Local Database
//private val myExerciseList: List<ExerciseItem> = listOf(
//    ExerciseItem(R.drawable.ic_baseline_image_24, "Push-Ups", arrayListOf("Chest", "Triceps"), "Plank position, press up"),
//    ExerciseItem(R.drawable.ic_baseline_favorite_24, "Pull-Ups", "Back", "Pull body up til chin reaches past bar"),
//    ExerciseItem(R.drawable.ic_baseline_fitness_center_24, "Wall Sits", "Legs", "Hold a 90-degree squat against wall"),
//    ExerciseItem(R.drawable.ic_baseline_image_24, "Arm Circles", "Shoulders", "Lateral raise of arms and move in circular motion"),
//    ExerciseItem(R.drawable.ic_baseline_favorite_24, "Burpees", "Full Body", "push-up to jump squat"),
//    ExerciseItem(R.drawable.ic_baseline_fitness_center_24, "Plank", "Core", "hold bridge postion on elbows"),
//    ExerciseItem(R.drawable.ic_baseline_image_24,"Crunches", "Core", "sit up while lying on back "),
//    ExerciseItem(R.drawable.ic_baseline_favorite_24, "Bicep Curls", "Biceps", "curl dumbbell while arms at sides"),
//    ExerciseItem(R.drawable.ic_baseline_fitness_center_24,"Skull Crushers", "Triceps", "lying on bench, barbell tricep extension behind the head"),
//    ExerciseItem(R.drawable.ic_baseline_image_24, "Mountain Climbers","Core", "knee drives in push-up position")
//)

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

private lateinit var exerciseViewModel: ExerciseViewModel

class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val REQUEST_CODE_EX = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //ITERATION 2: display list of exercises FROM DATABASE onto fragment
        //val exerciseList = myExerciseList

        val adapter = context?.let { ExerciseAdapter(it) }
        exercise_view.adapter = adapter
        exercise_view.layoutManager = LinearLayoutManager(context)
        exercise_view.setHasFixedSize(true)

        // HERE
        exerciseViewModel = ViewModelProvider(this).get(ExerciseViewModel::class.java)
        exerciseViewModel.allExercises.observe(this ,Observer { exercises ->
            exercises?.let { (exercise_view.adapter as ExerciseAdapter?)?.setExercises(it) }
        })

        // TO DO FOR ITERATION 2: floating action button functionality
        // - will open new fragment for user to input new Exercise Entry
        addExerciseButton.setOnClickListener {
            val i = Intent(activity, NewExerciseActivity::class.java)
            startActivityForResult(i, REQUEST_CODE_EX)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Request Validation: if request from intended activity and results were successful
        if (requestCode == REQUEST_CODE_EX && resultCode == Activity.RESULT_OK) {
            // extract Data received from New Exercise Activity
            val eName = data?.getStringExtra("eName").toString()
            val eType = data?.getStringExtra("eType").toString()
            val eDescr = data?.getStringExtra("eDescr").toString()

            //Toast.makeText(context, "NAME: $eName TYPE: $eType DESCR: $eDescr", Toast.LENGTH_LONG).show()

            // create exerciseItems based off data received
            val exerciseItem = ExerciseItem(R.drawable.ic_baseline_fitness_center_24, eName, eType, eDescr)

            // insert new exercise
            exerciseViewModel.insert(exerciseItem)

            //HomeFragment.apply {  }
        }
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}