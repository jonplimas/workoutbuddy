package com.example.workoutbuddy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.exercise_item.view.*

class WorkoutAdapterAdapter(private val workoutList: List<WorkoutItem>) :
    RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.exercise_item,
            parent, false)
        return ExerciseViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val currentItem = exerciseList[position]
        holder.imageView.setImageResource(currentItem.imageResource)
        holder.textView1.text = currentItem.name
        holder.textView2.text = currentItem.type
    }
    override fun getItemCount() = exerciseList.size

    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.image_view
        val textView1: TextView = itemView.exerciseName
        val textView2: TextView = itemView.exerciseType

        // TO DO FOR ITERATION 2: change toast to display item's actual description
        // Note: must wait for an actual library of exercises
        init {
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "Description: " + exerciseList[adapterPosition].name, Toast.LENGTH_SHORT).show()
            }
        }
    }
}