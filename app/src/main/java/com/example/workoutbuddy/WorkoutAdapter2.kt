package com.example.workoutbuddy

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutbuddy.Data.ExerciseItem
import com.example.workoutbuddy.Data.WorkoutItem


class WorkoutAdapter2 internal constructor(context: Context):
    RecyclerView.Adapter<WorkoutAdapter2.NewWorkoutViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var exercises = emptyList<ExerciseItem>()   // Cached copy of exercises
    private var setSecs = arrayOf("Sets", "Secs")



    @RequiresApi(Build.VERSION_CODES.M)
    inner class NewWorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.findViewById(R.id.exerciseName)
        val textView2: TextView = itemView.findViewById(R.id.exerciseType)
        val exCheckBox: CheckBox = itemView.findViewById(R.id.exerciseCheckbox)
        val numReps: EditText = itemView.findViewById(R.id.numReps)
        val numSets: EditText = itemView.findViewById(R.id.numRepsTime)
        val numSetsTime: Spinner = itemView.findViewById(R.id.spinnerRepsTime)


        init {
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "Exercise Description: " + exercises[adapterPosition].description, Toast.LENGTH_SHORT).show()
            }
        }


    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutAdapter2.NewWorkoutViewHolder {
        val itemView = inflater.inflate(R.layout.exercise_item_options, parent, false)
        return NewWorkoutViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WorkoutAdapter2.NewWorkoutViewHolder, position: Int) {
        val currentItem = exercises[position]
        holder.textView1.text = currentItem.name
        holder.textView2.text = currentItem.type
    }


    internal fun setExercises(exercises: List<ExerciseItem>) {
        this.exercises = exercises
        notifyDataSetChanged()
    }

    internal fun getExercise(position: Int): ExerciseItem {
        return this.exercises[position]
    }



    override fun getItemCount() = exercises.size

}