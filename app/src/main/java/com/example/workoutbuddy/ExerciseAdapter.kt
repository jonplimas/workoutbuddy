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
import com.example.workoutbuddy.Data.ExerciseItem
import com.example.workoutbuddy.activities.ExercisePopupWindow
import kotlinx.android.synthetic.main.exercise_item.view.*


class ExerciseAdapter internal constructor(context: Context) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var exercises = emptyList<ExerciseItem>()   // Cached copy of exercises
    private var heartClicked = false


    @RequiresApi(Build.VERSION_CODES.M)
    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.exerciseImage)
        val textView1: TextView = itemView.findViewById(R.id.exerciseName)
        val textView2: TextView = itemView.findViewById(R.id.exerciseType)


        // TO DO FOR ITERATION 2: change toast to display item's actual description
        // Note: must wait for an actual library of exercises
        init {
            itemView.setOnClickListener {
                //Toast.makeText(itemView.context, "Exercise Description: " + exercises[adapterPosition].description, Toast.LENGTH_SHORT).show()

                val i = Intent(itemView.context.applicationContext, ExercisePopupWindow::class.java)
                i.putExtra("eName", exercises[adapterPosition].name)
                i.putExtra("eDescr", exercises[adapterPosition].description)
                itemView.context.startActivity(i)

            }



            // Favorites action
//            itemView.imageHeartButton.setOnClickListener {
//                if(!heartClicked){
//                    itemView.imageHeartButton.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
//                    heartClicked = true
//                } else {
//                    itemView.imageHeartButton.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
//                    heartClicked = false
//                }
//            }



        }

    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        //val itemView = LayoutInflater.from(parent.context).inflate(R.layout.exercise_item, parent, false)
        val itemView = inflater.inflate(R.layout.exercise_item, parent, false)
        return ExerciseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val currentItem = exercises[position]
        holder.imageView.setImageResource(currentItem.imageResource)
        holder.textView1.text = currentItem.name
        holder.textView2.text = currentItem.type
    }

    internal fun setExercises(exercises: List<ExerciseItem>) {
        this.exercises = exercises
        notifyDataSetChanged()
    }

    override fun getItemCount() = exercises.size



}

