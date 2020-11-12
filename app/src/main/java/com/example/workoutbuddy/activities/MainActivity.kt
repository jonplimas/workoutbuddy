package com.example.workoutbuddy.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.workoutbuddy.R
import com.example.workoutbuddy.fragments.FavoritesFragment
import com.example.workoutbuddy.fragments.HomeFragment
import com.example.workoutbuddy.fragments.UserFragment
import com.example.workoutbuddy.fragments.WorkoutsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = WorkoutsFragment()
//        val favoritesFragment = FavoritesFragment()
        val workoutsFragment = HomeFragment()
        val userFragment = UserFragment()


        //set HomeFragment as initial view
        makeCurrentFragment(homeFragment)

        //display each fragment when clicked on
        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> makeCurrentFragment(homeFragment)
//                R.id.navigation_favorite -> makeCurrentFragment(favoritesFragment)
                R.id.navigation_workout -> makeCurrentFragment(workoutsFragment)
                R.id.navigation_user -> makeCurrentFragment(userFragment)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            addToBackStack(null)
            commit()
        }
}