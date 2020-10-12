package com.example.workoutbuddy

import android.graphics.Color
import android.graphics.ColorFilter
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.exercise_item.view.*


class ExerciseAdapter(private val exerciseList: List<ExerciseItem>) :
    RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {
    private var heartClicked = false

    @RequiresApi(Build.VERSION_CODES.M)
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

    @RequiresApi(Build.VERSION_CODES.M)
    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.exerciseImage
        val textView1: TextView = itemView.exerciseName
        val textView2: TextView = itemView.exerciseType

        // TO DO FOR ITERATION 2: change toast to display item's actual description
        // Note: must wait for an actual library of exercises

        // ITERATION 1: Use toast to check if appropriate values of ExerciseItems can accessed
        init {
            itemView.setOnClickListener {
                 Toast.makeText(itemView.context, "Exercise Description: " + exerciseList[adapterPosition].description, Toast.LENGTH_SHORT).show()
            }
            itemView.imageHeartButton.setOnClickListener {
                if(heartClicked == false){
                    itemView.imageHeartButton.setBackgroundColor(Color.RED)
                    heartClicked = true
                } else {
                    itemView.imageHeartButton.setBackgroundColor(Color.TRANSPARENT)
                    heartClicked = false
                }

            }

        }
    }
}