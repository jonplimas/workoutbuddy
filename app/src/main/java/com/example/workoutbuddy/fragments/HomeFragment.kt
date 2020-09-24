package com.example.workoutbuddy.fragments

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
import kotlinx.android.synthetic.main.fragment_home.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

// TO DO FOR ITERATION 1: implement a hardcoded list of 10 Exercise Items HERE


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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

        // display list of exercises to display onto fragment
        // genDummyList = populates the screen to make a placeholder list of 10 exercises
        // TO DO FOR ITERATION 1: replace dummy list with hardcoded list of exercises
        val exerciseList = generateDummyList(10)
        exercise_view.adapter = ExerciseAdapter(exerciseList)
        exercise_view.layoutManager = LinearLayoutManager(context)
        exercise_view.setHasFixedSize(true)


        // TO DO FOR ITERATION 2: floating action button functionality
        // will open new fragment for user to input new Exercise Entry
        addExerciseButton.setOnClickListener {
            Toast.makeText(context,"Add new Exercise HERE." , Toast.LENGTH_SHORT).show()
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

    // TO DO FOR ITERATION 1: reference this function for WorkoutsFragment.kt
    // comment out this function when done
    // fill list with placeholder data
    private fun generateDummyList(size: Int): List<ExerciseItem> {
        val list = ArrayList<ExerciseItem>()
        for (i in 0 until size) {
            val drawable = when (i % 3) {
                // sets image for each item.
                0 -> R.drawable.ic_baseline_image_24
                1 -> R.drawable.ic_baseline_favorite_24
                else -> R.drawable.ic_baseline_fitness_center_24
            }
            // sets the text of each heading: item position number, subheading: line 2
            val item = ExerciseItem(drawable, "Item $i", "Line 2")
            list += item
        }
        // return populated list
        return list
    }

}