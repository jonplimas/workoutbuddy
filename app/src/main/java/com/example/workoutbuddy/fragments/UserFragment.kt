package com.example.workoutbuddy.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutbuddy.Data.WorkoutItem
import com.example.workoutbuddy.ExerciseAdapter
import com.example.workoutbuddy.R
import com.example.workoutbuddy.UserRecentsAdapter
import com.example.workoutbuddy.ViewModels.ExerciseViewModel
import com.example.workoutbuddy.ViewModels.WorkoutViewModel
import com.example.workoutbuddy.activities.AchievementsActivity
import com.example.workoutbuddy.activities.LoginSignup.LoginActivity
import com.example.workoutbuddy.activities.MainActivity
import kotlinx.android.synthetic.main.activity_start_workout.*
import kotlinx.android.synthetic.main.fragment_exercises.*
import kotlinx.android.synthetic.main.fragment_user.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UserFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var userName: String? = null


    //private lateinit var rWorkoutList: ArrayList<WorkoutItem>
    private lateinit var rWorkoutViewModel: WorkoutViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        userName = arguments?.getString("uName")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //val mainActivity = MainActivity()
        // name.text = mainActivity.getUserNameFromMain()
        //name.text = arguments?.getString("uName")

        val adapter = context?.let { UserRecentsAdapter(it) }
        recentworkoutrecycler.adapter = adapter
        recentworkoutrecycler.layoutManager = LinearLayoutManager(context)
        recentworkoutrecycler.setHasFixedSize(true)

        // TODO: Create new view model for
        rWorkoutViewModel = ViewModelProvider(this).get(WorkoutViewModel::class.java)
        rWorkoutViewModel.recentWorkouts.observe(this , Observer { rWorkouts ->
            rWorkouts?.let { (recentworkoutrecycler.adapter as UserRecentsAdapter?)?.setWorkouts(it) }
        })




        //Onclick navigates to the achievements activity
        achievementnav.setOnClickListener {
            //Toast.makeText(activity, "clicked achievements tab", Toast.LENGTH_SHORT).show()
            val intent = Intent(activity, AchievementsActivity::class.java)
            startActivity(intent)
        }


        logoutButton.setOnClickListener {
            val i = Intent(activity, LoginActivity::class.java)
            startActivity(i)
            activity?.finish()
        }





    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UserFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}