package com.example.workoutbuddy.Data

import androidx.lifecycle.LiveData

class WorkoutRepository(private val workoutDao: WorkoutDao) {

    val allWorkouts: LiveData<List<WorkoutItem>> = workoutDao.getAlphabetizedWorkouts()
    val recentWorkouts: LiveData<List<WorkoutItem>> = workoutDao.getRecentWorkouts()

    suspend fun insertWorkout(workout: WorkoutItem) {
        workoutDao.insertWorkout(workout)
    }
}