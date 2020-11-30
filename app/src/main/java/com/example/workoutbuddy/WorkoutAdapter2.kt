package com.example.workoutbuddy

import android.content.Context
import android.os.Build
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutbuddy.Data.ExerciseItem
import kotlinx.android.synthetic.main.activity_new_workout.*
import kotlinx.android.synthetic.main.exercise_item_options.view.*


class WorkoutAdapter2 internal constructor(context: Context):
    RecyclerView.Adapter<WorkoutAdapter2.NewWorkoutViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val mContext = context
    var checkBoxStateArray = SparseBooleanArray()
    private var exercises = emptyList<ExerciseItem>()   // Cached copy of exercises
    private var repSecs = arrayOf("Reps", "Secs")



    @RequiresApi(Build.VERSION_CODES.M)
    inner class NewWorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        AdapterView.OnItemSelectedListener {
        val textView1: TextView = itemView.findViewById(R.id.exerciseName)
        val textView2: TextView = itemView.findViewById(R.id.exerciseType)
        val exCheckBox: CheckBox = itemView.findViewById(R.id.exerciseCheckbox)
        val numSets: EditText = itemView.findViewById(R.id.numReps)
        val numReps: EditText = itemView.findViewById(R.id.numRepsTime)
        val numSetsTime: Spinner = itemView.findViewById(R.id.spinnerRepsTime)



        init {
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "Exercise Description: " + exercises[adapterPosition].description, Toast.LENGTH_SHORT).show()
            }

            // Set Spinner for Set Quantifier
            numSetsTime.onItemSelectedListener = this
            val aa = ArrayAdapter(mContext, android.R.layout.simple_spinner_item, repSecs)
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Set Adapter to Spinner
            numSetsTime.adapter = aa



            // CheckBox Listener
            exCheckBox.setOnClickListener {
                if(!checkBoxStateArray.get(adapterPosition, false)) {
                    exCheckBox.isChecked = true
                    checkBoxStateArray.put(adapterPosition, true)
                } else {
                    exCheckBox.isChecked = false
                    checkBoxStateArray.put(adapterPosition, false)
                }
            }

        }

        override fun onNothingSelected(parent: AdapterView<*>?) {

        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, positon: Int, id: Long) {
            // use position to know the selected item
            exercises[adapterPosition].setQuantifier = parent!!.selectedItem.toString()
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
        holder.exCheckBox.isChecked = checkBoxStateArray.get(position, false)

        holder.numSets.addTextChangedListener {
            currentItem.sets = holder.numSets.text.toString()
        }
        holder.numReps.addTextChangedListener{
            currentItem.reps = holder.numReps.text.toString()
        }
        //currentItem.setQuantifier = holder.numSetsTime.selectedItem.toString()

    }

    override fun getItemCount() = exercises.size

    internal fun setExercises(exercises: List<ExerciseItem>) {
        this.exercises = exercises
        notifyDataSetChanged()
    }

    internal fun getExercise(position: Int): ExerciseItem {
        return this.exercises[position]
    }


}