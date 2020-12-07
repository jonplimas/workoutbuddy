package com.example.workoutbuddy.Data

import androidx.lifecycle.LiveData

class WorkoutRepository(private val workoutDao: WorkoutDao, private val routineDao: RoutineDao) {

    val allWorkouts: LiveData<List<WorkoutItem>> = workoutDao.getAlphabetizedWorkouts()
    val recentWorkouts: LiveData<List<WorkoutItem>> = workoutDao.getRecentWorkouts()

    val allRoutines: LiveData<List<Routine>> = routineDao.getAlphabetizedRoutines()

    suspend fun insertWorkout(workout: WorkoutItem) { workoutDao.insertWorkout(workout) }

    suspend fun insertRoutine(routine: Routine) { routineDao.insertRoutine(routine) }

    fun deleteWorkout(workout: WorkoutItem) {
        val wID = workout.workoutID
        workoutDao.deleteWorkout(workout)
        routineDao.deleteRoutinesByWorkoutID(wID)
    }


}