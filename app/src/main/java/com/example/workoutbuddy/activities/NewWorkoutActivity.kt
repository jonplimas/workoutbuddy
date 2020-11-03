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
import com.example.workoutbuddy.R
import kotlinx.android.synthetic.main.activity_new_workout.*

class NewWorkoutActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private var listOfItems = arrayOf("Full Body", "Upper Body", "Lower Body", "Core")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_workout)

        val newWorkoutName: EditText = findViewById(R.id.workoutName)
        val newWorkoutDescr: EditText = findViewById(R.id.newWorkoutDescription)


        spinner1!!.onItemSelectedListener = this

        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, listOfItems)
        // Set layout to use when the list of choices appear
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        spinner1!!.adapter = aa


        startActBtn.setOnClickListener {
            onBackPressed()
        }

        submitNewUserButton.setOnClickListener {
            // Input Validation: CANCEL IF ANYTHING IS MISSING
            if (TextUtils.isEmpty(newWorkoutName.text) || TextUtils.isEmpty(newWorkoutDescr.text) || spinner1.selectedItem.toString().isEmpty()) {
                Toast.makeText(this, "SOMETHING EMPTY, PLEASE FILL OUT ENTRY COMPLETELY", Toast.LENGTH_SHORT).show()
            } else {
                val i = Intent(this, NewWorkoutActivity2::class.java)
                val wName = newWorkoutName.text.toString()
                val wType = spinner1.selectedItem.toString()
                val wDescr = newWorkoutDescr.text.toString()

                Toast.makeText(this, "NAME: $wName TYPE: $wType DESCR: $wDescr", Toast.LENGTH_LONG).show()

                i.putExtra("wName", wName)
                i.putExtra("wType", wType)
                i.putExtra("wDescr", wDescr)
                startActivity(i)
            }
        }

    }

    override fun onItemSelected(adapterView: AdapterView<*>, view: View, position: Int, id: Long) {
        // use position to know the selected item

    }

    override fun onNothingSelected(arg0: AdapterView<*>) {

    }
}