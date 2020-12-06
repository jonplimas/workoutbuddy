package com.example.workoutbuddy.activities.WorkoutNav

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.workoutbuddy.Data.Badge
import com.example.workoutbuddy.R
import com.example.workoutbuddy.ViewModels.BadgeViewModel
import com.example.workoutbuddy.fragments.HomeFragment

class EndWorkoutActivity : AppCompatActivity() {

    private lateinit var badgeViewModel: BadgeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_workout)

        val finishWorkoutButton = findViewById<Button>(R.id.finishWorkoutBtn)

        val bundle = intent.extras
        val workoutCompleted = bundle?.getBoolean("completed")
        val workoutType = bundle?.getString("type")

        Toast.makeText(this, "Complete? $workoutCompleted", Toast.LENGTH_SHORT).show()


        badgeViewModel = ViewModelProvider(this).get(BadgeViewModel::class.java)
        badgeViewModel.allBadges.observe(this, Observer { badges ->
            if (workoutCompleted!!){
                badges[0].count += 1

                //Toast.makeText(this, "${badges[0].count} ", Toast.LENGTH_SHORT).show()

                when (workoutType) {
                    "Full Body" -> {
                        badges[1].count += 1
                        badges[2].count += 1
                        badges[3].count += 1
                    }
                    "Upper Body" -> {
                        badges[4].count += 1
                        badges[5].count += 1
                        badges[6].count += 1
                    }
                    "Core" -> {
                        badges[7].count += 1
                        badges[8].count += 1
                        badges[9].count += 1
                    }
                    "Lower Body" -> {
                        badges[10].count += 1
                        badges[11].count += 1
                        badges[12].count += 1
                    }
                }

            }

        })




        finishWorkoutButton.setOnClickListener {
            if (workoutCompleted!!){
                for (badge in badgeViewModel.allBadges.value!!){
                    badge?.let { badgeViewModel.updateBadge(it) }
                }
                //badgeViewModel.allBadges.value?.get(0)?.let { badgeViewModel.updateBadge(it) }

            }


            val i = Intent()
            setResult(Activity.RESULT_OK, i)
            finish()
        }
    }

}