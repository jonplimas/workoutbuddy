package com.example.workoutbuddy

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat.startActivity
import com.example.workoutbuddy.Data.WorkoutItem
import com.example.workoutbuddy.ViewModels.ExerciseViewModel
import com.example.workoutbuddy.ViewModels.WorkoutViewModel
import com.example.workoutbuddy.activities.WorkoutNav.StartWorkoutActivity

class WorkoutAdapter(var context: Context, var workoutList: List<WorkoutItem>, private val workoutViewModel: WorkoutViewModel): BaseAdapter() {

    override fun getItem(position: Int): Any {
        return workoutList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return workoutList.size
    }

    internal fun setWorkouts(workouts: List<WorkoutItem>) {
        this.workoutList = workouts
        notifyDataSetChanged()
    }


    internal fun removeWorkout(position: Int) {
        Toast.makeText(context, "DELETING: ${workoutList[position].name}...", Toast.LENGTH_LONG).show()
        workoutViewModel.deleteWorkout(workoutList[position])
        this.workoutList -= workoutList[position]
        notifyDataSetChanged()
    }

    fun getWorkoutAtPosition(position: Int): WorkoutItem {
        return workoutList[position]
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // inflate individual Workout Card
        val workoutCardView: View = View.inflate(context, R.layout.workout_item, null)

        // initialize items in relation to workout_item.xml
        val workoutImages: ImageView = workoutCardView.findViewById(R.id.workoutImage)
        val workoutNames: TextView = workoutCardView.findViewById(R.id.workoutName)
        val workoutCategories: TextView = workoutCardView.findViewById(R.id.workoutCat)
        val deleteButton: Button = workoutCardView.findViewById(R.id.deleteWButton)

        // initialize individual cards based on indices.
        val myWorkoutItem: WorkoutItem = workoutList[position]

        // fill workout card with data
        workoutImages.setImageResource(myWorkoutItem.workoutImageResource)
        workoutNames.text = myWorkoutItem.name
        workoutCategories.text = myWorkoutItem.category

        // Click to Start WorkoutNav
        workoutImages.setOnClickListener {
            //Toast.makeText(context, "Workout Description: " + workoutList[position].description, Toast.LENGTH_SHORT).show()
            val intent = Intent(context, StartWorkoutActivity::class.java)
            intent.putExtra("wName", workoutList[position].name)
            intent.putExtra("wCat", workoutList[position].category)
            intent.putExtra("wDesc", workoutList[position].description)
            intent.putExtra("wImage", workoutList[position].workoutImageResource)
            intent.putExtra("wID", workoutList[position].workoutID)
            startActivity(context,intent, Bundle.EMPTY)
        }

        deleteButton.setOnClickListener {
            removeWorkout(position)
        }


        return workoutCardView
    }
}

