package com.example.workoutbuddy.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.workoutbuddy.Data.WorkoutItem
import com.example.workoutbuddy.R
import com.example.workoutbuddy.ViewModels.ExerciseViewModel
import com.example.workoutbuddy.ViewModels.WorkoutViewModel
import kotlinx.android.synthetic.main.activity_new_workout.*

private lateinit var workoutViewModel: WorkoutViewModel


class NewWorkoutActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private val REQUEST_CODE_NW = 69
    private var listOfItems = arrayOf("Full Body", "Upper Body", "Lower Body", "Core")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_workout)

        val newWorkoutName: EditText = findViewById(R.id.workoutName)
        val newWorkoutDescr: EditText = findViewById(R.id.newWorkoutDescription)

        workoutViewModel = ViewModelProvider(this).get(WorkoutViewModel::class.java)


        spinner1!!.onItemSelectedListener = this

        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, listOfItems)
        // Set layout to use when the list of choices appear
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        spinner1!!.adapter = aa


        startActBtn.setOnClickListener {
            setResult(Activity.RESULT_FIRST_USER)
            onBackPressed()
        }

        submitNewUserButton.setOnClickListener {
            // Input Validation: CANCEL IF ANYTHING IS MISSING
            if (TextUtils.isEmpty(newWorkoutName.text) || TextUtils.isEmpty(newWorkoutDescr.text) || spinner1.selectedItem.toString().isEmpty()) {
                Toast.makeText(this, "SOMETHING EMPTY, PLEASE FILL OUT ENTRY COMPLETELY", Toast.LENGTH_SHORT).show()
            } else {
                // Create new workout to insert into DB
                val newWorkout = WorkoutItem(workoutImageResource = R.drawable.ic_baseline_fitness_center_24,
                    workoutCreatorID = 69,
                    name = newWorkoutName.text.toString(),
                    category = spinner1.selectedItem.toString(),
                    description = newWorkoutDescr.text.toString()
                )
                // Update DB with new workout
                workoutViewModel.insertWorkout(newWorkout)

                // Create Intent and store new workout data
                val i = Intent(this, NewWorkoutActivity2::class.java)
                val wID = newWorkout.workoutID
                val wName = newWorkoutName.text.toString()
                val wType = spinner1.selectedItem.toString()
                val wDescr = newWorkoutDescr.text.toString()

                // Toast.makeText(this, "NAME: $wName TYPE: $wType DESCR: $wDescr", Toast.LENGTH_LONG).show()

                // Pack intent with new workout data to pass to NW2 activity
                i.putExtra("wID", wID)
                i.putExtra("wName", wName)
                i.putExtra("wType", wType)
                i.putExtra("wDescr", wDescr)

                setResult(Activity.RESULT_OK)
                startActivity(i)
                finish()
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_NW && resultCode == Activity.RESULT_OK) {
            val replyIntent = Intent()

            replyIntent.putExtra( "wName", data?.getStringExtra("wName").toString())
            replyIntent.putExtra( "wCategory", data?.getStringExtra("wCategory").toString())
            replyIntent.putExtra("wDescription",data?.getStringExtra("wDescription").toString())
//            replyIntent.putExtra("xNames", data?.getStringArrayExtra("xNames"))
//            replyIntent.putExtra("xTypes", data?.getStringArrayExtra("xTypes"))
//            replyIntent.putExtra("xDescr", data?.getStringArrayExtra("xDescripts"))
            setResult(Activity.RESULT_OK, replyIntent)
            //finish()
        } else {
            Toast.makeText(this,"COULD NOT PULL", Toast.LENGTH_LONG).show()
        }

    }

    override fun onItemSelected(adapterView: AdapterView<*>, view: View, position: Int, id: Long) {
        // use position to know the selected item

    }

    override fun onNothingSelected(arg0: AdapterView<*>) {

    }
}