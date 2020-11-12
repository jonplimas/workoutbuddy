package com.example.workoutbuddy.activities

import android.os.Bundle
import android.widget.Button
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.workoutbuddy.BadgeAdapter
import com.example.workoutbuddy.Data.Badge
import com.example.workoutbuddy.Data.WorkoutItem
import com.example.workoutbuddy.R
import com.example.workoutbuddy.ViewModels.WorkoutViewModel
import com.example.workoutbuddy.WorkoutAdapter
import kotlinx.android.synthetic.main.activity_achievements.*

class AchievementsActivity : AppCompatActivity() {

    private lateinit var mBadgeList: ArrayList<Badge>
    private lateinit var gridView: GridView
    private lateinit var badgeAdapter: BadgeAdapter

    // private lateinit var badgeViewModel: BadgeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_achievements)

        //Display GridView
        mBadgeList = generateDummyList(7)
        gridView = findViewById(R.id.badgeGridView)
        badgeAdapter = BadgeAdapter(this, mBadgeList)
        gridView.adapter = badgeAdapter

        gridView.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(this, mBadgeList[i].description, Toast.LENGTH_SHORT).show()
        }



        // back button to return to User Page
        startActBtn.setOnClickListener {
            finish()
        }






    }


    // fill Workout List with placeholder data
    private fun generateDummyList(size: Int): ArrayList<Badge> {

        val list = ArrayList<Badge>()

        for (i in 0 until size) {
            val drawable = when (i % 3) {
                // sets image for each item.
                0 -> R.drawable.ic_baseline_image_24
                1 -> R.drawable.ic_baseline_favorite_24
                else -> R.drawable.ic_baseline_fitness_center_24
            }
            // sets the text of each heading: item position number + 1, subheading: category position number
            val item = Badge(
                drawable,
                "Badge $i",
                "Complete 1 out of $i.",
                1,
                i)
            list += item
        }
        // return populated list
        return list
    }

}