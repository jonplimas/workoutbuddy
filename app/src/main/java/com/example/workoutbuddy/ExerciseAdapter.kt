package com.example.workoutbuddy

import android.content.Context
import android.graphics.Color
import android.graphics.ColorFilter
import android.os.Build
import android.transition.Slide
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutbuddy.activities.MainActivity
import com.example.workoutbuddy.fragments.HomeFragment
import kotlinx.android.synthetic.main.exercise_item.view.*


class ExerciseAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {


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
                Toast.makeText(itemView.context, "Exercise Description: " + exercises[adapterPosition].description, Toast.LENGTH_SHORT).show()
//
//                val inflator: LayoutInflater = LayoutInflater.from(itemView.context)
//
//                // Inflate a custom view using layout inflater
//                val view = inflator.inflate(R.layout.exercise_popup_card, null)
//
//                // Initialize a new instance of popup window
//                val popupWindow = PopupWindow(
//                    it, // Custom view to show in popup window
//                    LinearLayout.LayoutParams.WRAP_CONTENT, // Width of popup window
//                    LinearLayout.LayoutParams.WRAP_CONTENT // Window height
//                )
//
//                // Set an elevation for the popup window
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    popupWindow.elevation = 10.0F
//                }
//
//                // If API level 23 or higher then execute the code
//                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//                    // Create a new slide animation for popup window enter transition
//                    val slideIn = Slide()
//                    slideIn.slideEdge = Gravity.TOP
//                    popupWindow.enterTransition = slideIn
//
//                    // Slide animation for popup window exit transition
//                    val slideOut = Slide()
//                    slideOut.slideEdge = Gravity.RIGHT
//                    popupWindow.exitTransition = slideOut
//
//                }
//
//                // Get the widgets reference from custom view
//                val exNameTV = view.findViewById<TextView>(R.id.popuptitle)
//                // val exLengthTV = view.findViewById<TextView>(R.id.popuptitlebelow)
//                val exDescrTV = view.findViewById<TextView>(R.id.popupdesc)
//                val buttonPopup = view.findViewById<Button>(R.id.popupbttn)
//
//                exNameTV.text = exercises[adapterPosition].name
//                exDescrTV.text = exercises[adapterPosition].description
//
//
//                // Set on click listener to dismiss popup
//                buttonPopup.setOnClickListener{
//                    popupWindow.dismiss()
//                }
            }


            // favorites action
            itemView.imageHeartButton.setOnClickListener {
                if(!heartClicked){
                    itemView.imageHeartButton.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
                    heartClicked = true
                } else {
                    itemView.imageHeartButton.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
                    heartClicked = false
                }

            }

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

