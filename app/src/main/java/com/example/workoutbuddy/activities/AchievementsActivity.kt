package com.example.workoutbuddy.activities

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.workoutbuddy.R
import kotlinx.android.synthetic.main.activity_achievements.*

class AchievementsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_achievements)

        // back button to return to User Page
        startActBtn.setOnClickListener {
            finish()
        }






    }
}