package com.example.workoutbuddy

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutbuddy.Data.WorkoutItem
import com.example.workoutbuddy.activities.WorkoutNav.StartWorkoutActivity


class UserRecentsAdapter internal constructor(context: Context):
    RecyclerView.Adapter<UserRecentsAdapter.RecentsViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var recentWorkouts = emptyList<WorkoutItem>()   // Cached copy of workouts
    private val mContext = context


    @RequiresApi(Build.VERSION_CODES.M)
    inner class RecentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.findViewById(R.id.pizza)


        init {
            itemView.setOnClickListener {
                // Toast.makeText(itemView.context, "Workout Description: " + recentWorkouts[adapterPosition].description, Toast.LENGTH_SHORT).show()

                val i = Intent(mContext, StartWorkoutActivity::class.java)
                i.putExtra("wName", recentWorkouts[adapterPosition].name)
                i.putExtra("wDesc", recentWorkouts[adapterPosition].description)
                i.putExtra("wID", recentWorkouts[adapterPosition].workoutID)
                mContext.startActivity(i)

            }
        }


    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRecentsAdapter.RecentsViewHolder {
        val itemView = inflater.inflate(R.layout.workout_item_simple, parent, false)
        return RecentsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserRecentsAdapter.RecentsViewHolder, position: Int) {
        val currentItem = recentWorkouts[position]
        holder.textView1.text = currentItem.name


    }


    internal fun setWorkouts(exercises: List<WorkoutItem>) {
        this.recentWorkouts = exercises
        notifyDataSetChanged()
    }

    internal fun getWorkouts(position: Int): WorkoutItem {
        return this.recentWorkouts[position]
    }



    override fun getItemCount() = recentWorkouts.size

}