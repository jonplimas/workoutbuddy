package com.example.workoutbuddy.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.workoutbuddy.Data.ExerciseItem
import com.example.workoutbuddy.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavoritesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

private val exerciseTab = R.id.favExercisesTab
private val workoutTab = R.id.favWorkoutsTab

class FavoritesFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        //UNCOMMENT THIS TO DISPLAY EXERCISES LIST. (uncomment from activity_main.xml as well.)
        //populates the screen to make a placeholder list of exercises

        //        val exerciseList = generateDummyList(5)
        //        exercise_view.adapter = ExerciseAdapter(exerciseList)
        //        exercise_view.layoutManager = LinearLayoutManager(this)
        //        exercise_view.setHasFixedSize(true)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavoritesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavoritesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    //fill list with placeholder data
    private fun generateDummyList(size: Int): List<ExerciseItem> {
        val list = ArrayList<ExerciseItem>()
        for (i in 0 until size) {
            val drawable = when (i % 3) {
                //sets image for each item.
                0 -> R.drawable.ic_baseline_image_24
                1 -> R.drawable.ic_baseline_favorite_24
                else -> R.drawable.ic_baseline_fitness_center_24
            }
            //sets the text of each heading: item position number, subheading: line 2
            val item = ExerciseItem(
                drawable,0,
                "Item $i",
                "Line 2"
            )
            list += item
        }
        //return populated list
        return list
    }
}