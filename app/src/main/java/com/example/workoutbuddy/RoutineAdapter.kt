package com.example.workoutbuddy

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutbuddy.Data.Routine

class RoutineAdapter internal constructor(context: Context) : RecyclerView.Adapter<RoutineAdapter.RoutineViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var routines = emptyList<Routine>()   // Cached copy of Routines

    @RequiresApi(Build.VERSION_CODES.M)
    inner class RoutineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTV: TextView = itemView.findViewById(R.id.ex_text)
        val setsTV: TextView = itemView.findViewById(R.id.ex_sets)
        val repsTV: TextView = itemView.findViewById(R.id.ex_reps)

        init {
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "Routine Description: " + routines[adapterPosition].description, Toast.LENGTH_SHORT).show()
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutineAdapter.RoutineViewHolder {
        val itemView = inflater.inflate(R.layout.exercise_item_startworkout, parent, false)
        return RoutineViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RoutineAdapter.RoutineViewHolder, position: Int) {
        val currentItem = routines[position]
        holder.nameTV.text = currentItem.name
        holder.setsTV.text = currentItem.sets.toString() + " Sets"
        holder.repsTV.text =  "${currentItem.reps.toString()} ${currentItem.setQuantifier}"
    }

    override fun getItemCount() = routines.size

    fun getRoutineAtPosition(position: Int): Routine {
        return routines[position]
    }

    internal fun setRoutines(routines: List<Routine>) {
        this.routines = routines
        notifyDataSetChanged()
    }

    internal fun insertRoutine(routine: Routine) {
        this.routines += routine
        notifyDataSetChanged()
    }






}