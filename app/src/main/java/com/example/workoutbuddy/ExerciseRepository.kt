package com.example.workoutbuddy

import androidx.lifecycle.LiveData

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class ExerciseRepository(private val exerciseDao: ExerciseDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allWords: LiveData<List<ExerciseItem>> = exerciseDao.getAlphabetizedExercises()

    // suspend modifier tells the compiler that this needs to be called from a coroutine or another suspending function.
    suspend fun insertExercise(exercise: ExerciseItem) {
        exerciseDao.insertExercise(exercise)
    }
}