package com.example.workoutbuddy.Data

import androidx.lifecycle.LiveData

class RoutineRepository(private val routineDao: RoutineDao, private val workoutDao: WorkoutDao) {


    // private var x by Delegates.notNull<Int>()
    val allRoutines: LiveData<List<Routine>> = routineDao.getRoutines()
    val allWorkouts: LiveData<List<WorkoutItem>> = workoutDao.getAlphabetizedWorkouts()


    // suspend modifier tells the compiler that this needs to be called from a coroutine or another suspending function.
    suspend fun insertRoutine(routine: Routine) {
        routineDao.insertRoutine(routine)
    }

    fun deleteRoutinesByWorkoutID(workoutID: Int) {
        routineDao.deleteRoutinesByWorkoutID(workoutID)
    }

    fun getRoutinesByWorkoutID(workoutID: String) {
        routineDao.getRoutinesByWorkoutID(workoutID)

    }


}