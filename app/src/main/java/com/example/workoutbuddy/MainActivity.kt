package com.example.workoutbuddy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.workoutbuddy.fragments.FavoritesFragment
import com.example.workoutbuddy.fragments.HomeFragment
import com.example.workoutbuddy.fragments.UserFragment
import com.example.workoutbuddy.fragments.WorkoutsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val favoritesFragment = FavoritesFragment()
        val workoutsFragment = WorkoutsFragment()
        val userFragment = UserFragment()


        //set HomeFragment as initial view
        makeCurrentFragment(homeFragment)

        //display each fragment when clicked on
        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> makeCurrentFragment(homeFragment)
                R.id.navigation_favorite -> makeCurrentFragment(favoritesFragment)
                R.id.navigation_workout -> makeCurrentFragment(workoutsFragment)
                R.id.navigation_user -> makeCurrentFragment(userFragment)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
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
            val item = ExerciseItem(drawable, "Item $i", "Line 2")
            list += item
        }
        //return populated list
        return list
    }


}