package com.example.workoutbuddy.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.workoutbuddy.R
import com.example.workoutbuddy.WorkoutAdapter
import com.example.workoutbuddy.WorkoutItem
import com.example.workoutbuddy.activities.NewWorkoutActivity
import com.example.workoutbuddy.activities.WorkoutNav.StartWorkoutActivity
import kotlinx.android.synthetic.main.fragment_workouts.*

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
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // implemented a GridLayout in fragments_workouts.xml
        //      added and label Gridview into Relative Layout
        // implemented a WorkoutAdapter IN A NEW KOTLIN FILE
        //      used BaseAdapter as a reference
        // create function to generate dummy list of Workouts
        // implemented GridView with Adapter for Workouts HERE
        //gridView = grid_view

        mWorkoutList = generateDummyList(7)
        mWorkoutList[0] = WorkoutItem(R.drawable.ic_baseline_image_24, "Ab Circuit","Core", "Quick ab workout! Repeat every other day.")
        workoutAdapter = WorkoutAdapter(requireContext(), mWorkoutList)
        grid_view.adapter = workoutAdapter


        // Onclick listener to Start a Workout
//        grid_view.setOnClickListener {
//            val intent = Intent(activity, StartWorkoutActivity::class.java)
//            intent.putExtra("wName", mWorkoutList[0].name)
//            intent.putExtra("wCategory", mWorkoutList[0].category)
//            intent.putExtra("wDesc",  mWorkoutList[0].description)
//            intent.putExtra("wImage", mWorkoutList[0].workoutImageResource)
//            intent.putExtra("wReps", mWorkoutList[0].reps)
//            intent.putExtra("wSets", mWorkoutList[0].sets)
//            startActivity(intent)
//        }
            grid_view.setOnItemClickListener { adapterView, view, i, l ->
            //Toast.makeText(activity, "Workout Description: " + mWorkoutList[i].category, Toast.LENGTH_SHORT).show()
            val intent = Intent(activity, StartWorkoutActivity::class.java)
            intent.putExtra("wName", mWorkoutList[i].name)
            intent.putExtra("wCategory", mWorkoutList[i].category)
            intent.putExtra("wDesc",  mWorkoutList[i].description)
            intent.putExtra("wImage", mWorkoutList[i].workoutImageResource)
            intent.putExtra("wReps", mWorkoutList[i].reps)
            intent.putExtra("wSets", mWorkoutList[i].sets)
            startActivity(intent)
        }


        // TO DO FOR ITERATION 3: floating action button functionality
        // will open new Activity for user to input new Workout
        addWorkoutButton.setOnClickListener {
            // Toast.makeText(context,"Add new Workout HERE." , Toast.LENGTH_SHORT).show()
            val i = Intent(activity, NewWorkoutActivity::class.java)
            startActivityForResult(i, REQUEST_CODE_W)
        }
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == R.id.popupbttn){
//        }
//        return super.onOptionsItemSelected(item)
//    }



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
            val item = WorkoutItem(drawable, "Workout $j", "Category: $i")
            list += item
        }
        // return populated list
        return list
    }
}