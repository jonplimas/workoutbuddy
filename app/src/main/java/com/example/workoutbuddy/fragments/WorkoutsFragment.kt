package com.example.workoutbuddy.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.workoutbuddy.R
import com.example.workoutbuddy.WorkoutAdapter
import com.example.workoutbuddy.Data.WorkoutItem
import com.example.workoutbuddy.ViewModels.WorkoutViewModel
import com.example.workoutbuddy.activities.NewExerciseActivity.Companion.EXTRA_REPLY
import com.example.workoutbuddy.activities.NewWorkoutActivity
import com.example.workoutbuddy.activities.NewWorkoutActivity2
import com.example.workoutbuddy.activities.WorkoutNav.StartWorkoutActivity
import kotlinx.android.synthetic.main.fragment_workouts.*
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WorkoutsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */




class WorkoutsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val REQUEST_CODE_W = 5

    private lateinit var mWorkoutList: ArrayList<WorkoutItem>
    private lateinit var gridView: GridView
    private lateinit var workoutAdapter: WorkoutAdapter

    private lateinit var workoutViewModel: WorkoutViewModel


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
        return inflater.inflate(R.layout.fragment_workouts, container, false)
    }

    // NOTE: can only access recyclerView AFTER you create the view and inflate the layout of the fragment
    @SuppressLint("FragmentLiveDataObserve")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // implemented a GridLayout in fragments_workouts.xml
        //      added and label Gridview into Relative Layout
        // implemented a WorkoutAdapter IN A NEW KOTLIN FILE
        //      used BaseAdapter as a reference
        // create function to generate dummy list of Workouts
        // implemented GridView with Adapter for Workouts HERE
        //gridView = grid_view

        mWorkoutList = generateDummyList(10)
//        mWorkoutList[0] = WorkoutItem(
//            R.drawable.ic_baseline_image_24,
//            1,
//            1,
//            "Ab Circuit",
//            "Core",
//            "Quick ab workout! Repeat every other day."
//        )



        workoutAdapter = WorkoutAdapter(requireContext(), mWorkoutList)
        grid_view.adapter = workoutAdapter

        workoutViewModel = ViewModelProvider(this).get(WorkoutViewModel::class.java)
        workoutViewModel.allWorkouts.observe(this, Observer { workouts ->
            workouts?.let { workoutAdapter.setWorkouts(it) }
        })

//        // Start Workout By Clicking on an item
//        grid_view.setOnItemClickListener { adapterView, view, i, l ->
//            //Toast.makeText(activity, "Workout Description: " + mWorkoutList[i].category, Toast.LENGTH_SHORT).show()
//            val intent = Intent(activity, StartWorkoutActivity::class.java)
//            intent.putExtra("wName", mWorkoutList[i].name)
//            intent.putExtra("wCategory", mWorkoutList[i].category)
//            intent.putExtra("wDesc",  mWorkoutList[i].description)
//            intent.putExtra("wImage", mWorkoutList[i].workoutImageResource)
//            //intent.putExtra("wReps", mWorkoutList[i].reps)
//            //intent.putExtra("wSets", mWorkoutList[i].sets)
//            startActivity(intent)
//        }


        // TO DO FOR ITERATION 3: floating action button functionality
        // will open new Activity for user to input new Workout
        addWorkoutButton.setOnClickListener {
            // Toast.makeText(context,"Add new Workout HERE." , Toast.LENGTH_SHORT).show()
            val i = Intent(activity, NewWorkoutActivity::class.java)
            startActivityForResult(i, REQUEST_CODE_W)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

            val wName = data?.getStringExtra("Name").toString()
            val wCat = data?.getStringExtra("Category").toString()
            val wDescr = data?.getStringExtra("Descr").toString()

            val newWorkout = WorkoutItem(R.drawable.ic_baseline_fitness_center_24, 99,
                wName, wCat, wDescr)

            workoutViewModel.insertWorkout(newWorkout)


            Toast.makeText(context,"name: $wName, cat: $wCat, descr: $wDescr", Toast.LENGTH_LONG).show()

    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WorkoutsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WorkoutsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    // fill Workout List with placeholder data
    private fun generateDummyList(size: Int): ArrayList<WorkoutItem> {
        val list = ArrayList<WorkoutItem>()
        for (i in 0 until size) {
            val drawable = when (i % 3) {
                // sets image for each item.
                0 -> R.drawable.ic_baseline_image_24
                1 -> R.drawable.ic_baseline_favorite_24
                else -> R.drawable.ic_baseline_fitness_center_24
            }
            // sets the text of each heading: item position number + 1, subheading: category position number
            val j = i + 1
            val item = WorkoutItem(
                drawable,
                0,
                "Workout $j",
                "Category: $i"
            )
            list += item
        }
        // return populated list
        return list
    }
}