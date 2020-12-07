package com.example.workoutbuddy.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.workoutbuddy.Data.ExerciseRoomDatabase
import com.example.workoutbuddy.Data.WorkoutItem
import com.example.workoutbuddy.Data.WorkoutRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkoutViewModel(application: Application): AndroidViewModel(application) {

    private val repository: WorkoutRepository

    val allWorkouts: LiveData<List<WorkoutItem>>
    val recentWorkouts: LiveData<List<WorkoutItem>>

    init {
        val workoutDao = ExerciseRoomDatabase.getDatabase(application, viewModelScope).workoutDao()
        repository = WorkoutRepository(workoutDao)
        allWorkouts = repository.allWorkouts
        recentWorkouts = repository.recentWorkouts

    }

    fun insertWorkout(workoutItem: WorkoutItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertWorkout(workoutItem)
    }

    fun deleteWorkout(workoutItem: WorkoutItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteWorkout(workoutItem)
    }
}