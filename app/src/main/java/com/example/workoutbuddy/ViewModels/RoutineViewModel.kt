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
    // val myRoutines: LiveData<List<Routine>>


    init {
        val routineDao = ExerciseRoomDatabase.getDatabase(application, viewModelScope).routineDao()
        repository = RoutineRepository(routineDao)
        allRoutines = repository.allRoutines


    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insertRoutine(routine: Routine) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertRoutine(routine)
    }

//    fun getRoutine(id: Int): LiveData<List<Routine>> {
//        return routines with workout id = id
//    }


}