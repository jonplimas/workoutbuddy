package com.example.workoutbuddy.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WorkoutDao {

    @Query("SELECT * from workout_table ORDER BY name ASC")
    fun getAlphabetizedWorkouts(): LiveData<List<WorkoutItem>>

    @Query("SELECT * from workout_table ORDER BY name ASC LIMIT 4")
    fun getRecentWorkouts(): LiveData<List<WorkoutItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWorkout(workoutItem: WorkoutItem)

    @Query("DELETE FROM workout_table")
    suspend fun deleteAllWorkouts()

//    @Query("SELECT * FROM exercise_table LIMIT 1")
//    fun getAnyWorkout(): Array<WorkoutItem>


}