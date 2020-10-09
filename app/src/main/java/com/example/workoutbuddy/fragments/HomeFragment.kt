package com.example.workoutbuddy.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutbuddy.ExerciseAdapter
import com.example.workoutbuddy.ExerciseItem
import com.example.workoutbuddy.R
import com.example.workoutbuddy.activities.NewExerciseActivity
import kotlinx.android.synthetic.main.fragment_home.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

// ITERATION 1: implemented a hardcoded list of 10 Exercise Items to simulate Local Database
private val myExerciseList: List<ExerciseItem> = listOf(
    ExerciseItem(R.drawable.ic_baseline_image_24, "Push-Ups", "Chest", "Plank position, press up"),
    ExerciseItem(R.drawable.ic_baseline_favorite_24, "Pull-Ups", "Back", "Pull body up til chin reaches past bar"),
    ExerciseItem(R.drawable.ic_baseline_fitness_center_24, "Wall Sits", "Legs", "Hold a 90-degree squat against wall"),
    ExerciseItem(R.drawable.ic_baseline_image_24, "Arm Circles", "Shoulders", "Lateral raise of arms and move in circular motion"),
    ExerciseItem(R.drawable.ic_baseline_favorite_24, "Burpees", "Full Body", "push-up to jump squat"),
    ExerciseItem(R.drawable.ic_baseline_fitness_center_24, "Plank", "Core", "hold bridge postion on elbows"),
    ExerciseItem(R.drawable.ic_baseline_image_24,"Crunches", "Core", "sit up while lying on back "),
    ExerciseItem(R.drawable.ic_baseline_favorite_24, "Bicep Curls", "Biceps", "curl dumbbell while arms at sides"),
    ExerciseItem(R.drawable.ic_baseline_fitness_center_24,"Skull Crushers", "Triceps", "lying on bench, barbell tricep extension behind the head"),
    ExerciseItem(R.drawable.ic_baseline_image_24, "Mountain Climbers","Core", "knee drives in push-up position")
)

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //ITERATION 1: display hard-coded list of exercises to display onto fragment
        val exerciseList = myExerciseList
        exercise_view.adapter = ExerciseAdapter(exerciseList)
        exercise_view.layoutManager = LinearLayoutManager(context)
        exercise_view.setHasFixedSize(true)

        // TO DO FOR ITERATION 2: floating action button functionality
        // - will open new fragment for user to input new Exercise Entry
        addExerciseButton.setOnClickListener {
            Toast.makeText(context,"Add new Exercise HERE." , Toast.LENGTH_SHORT).show()
            val i = Intent(activity, NewExerciseActivity::class.java)
            startActivityForResult(i, REQUEST_CODE_EX)
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