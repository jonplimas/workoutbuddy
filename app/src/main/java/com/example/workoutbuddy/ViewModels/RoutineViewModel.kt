package com.example.workoutbuddy.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.workoutbuddy.Data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoutineViewModel(application: Application): AndroidViewModel(application) {



    private val repository: RoutineRepository
    // Using LiveData and caching what our DAO returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allRoutines: LiveData<List<Routine>>
    val allWorkouts: LiveData<List<WorkoutItem>>


    init {
        val routineDao = ExerciseRoomDatabase.getDatabase(application, viewModelScope).routineDao()
        val workoutDao = ExerciseRoomDatabase.getDatabase(application, viewModelScope).workoutDao()
        repository = RoutineRepository(routineDao, workoutDao)
        allRoutines = repository.allRoutines
        allWorkouts = repository.allWorkouts


    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insertRoutine(routine: Routine) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertRoutine(routine)
    }



}