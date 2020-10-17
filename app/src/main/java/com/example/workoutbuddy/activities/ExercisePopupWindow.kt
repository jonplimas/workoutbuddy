package com.example.workoutbuddy.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.workoutbuddy.R

class ExercisePopupWindow : AppCompatActivity() {

    private var popupTitle = " "
    private var popupTitleBelow = " "
    private var popupButton = "Dismiss"




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0, 0)
        setContentView(R.layout.activity_exercise_popup_window)

        val bundle = intent.extras



    }
}