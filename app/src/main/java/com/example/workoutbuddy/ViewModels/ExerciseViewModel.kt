package com.example.workoutbuddy.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.workoutbuddy.Data.ExerciseItem
import com.example.workoutbuddy.Data.ExerciseRepository
import com.example.workoutbuddy.Data.ExerciseRoomDatabase
import com.example.workoutbuddy.Data.WorkoutItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExerciseViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ExerciseRepository
    // Using LiveData and caching what our DAO returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allExercises: LiveData<List<ExerciseItem>>
    val coreExercises: LiveData<List<ExerciseItem>>
    val upperExercises: LiveData<List<ExerciseItem>>
    val lowerExercises: LiveData<List<ExerciseItem>>


    init {
        val exerciseDao = ExerciseRoomDatabase.getDatabase(
            application,
            viewModelScope
        ).exerciseDao()
        repository =
            ExerciseRepository(exerciseDao)
        allExercises = repository.allExercises
        coreExercises = repository.coreExercises
        upperExercises = repository.upperExercises
        lowerExercises = repository.lowerExercises

    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insertExer(exerciseItem: ExerciseItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertExercise(exerciseItem)
    }

    fun deleteExer(exerciseItem: ExerciseItem) = viewModelScope.launch(Dispatchers.IO){
        repository.deleteExercise(exerciseItem)
    }


}