package com.example.workoutbuddy

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


// In the DAO (data access object), you specify SQL queries and associate them with method calls.
// The compiler checks the SQL and generates queries from convenience annotations for common queries.
@Dao
interface ExerciseDao {

    // Retrieves the entire list of Exercise in ABC order
    // Note: LiveData allows data to be observable and live changes can be made to UI upon db updates.
    @Query("SELECT * from exercise_table ORDER BY name ASC")
    fun getAlphabetizedExercises(): LiveData<List<ExerciseItem>>

    @Query("SELECT * from exercise_table WHERE type = 'Core' ORDER BY name ASC")
    fun getCoreExercises(): LiveData<List<ExerciseItem>>

    @Query("SELECT * from exercise_table WHERE type = 'Chest/Back' OR  ORDER BY name ASC")
    fun getUpperExercises(): LiveData<List<ExerciseItem>>

    @Query("SELECT * from exercise_table WHERE type = 'Glutes' ORDER BY name ASC")
    fun getLowerExercises(): LiveData<List<ExerciseItem>>


    // ConflictStrategy: ignores new word if it has the same name as one already in the list
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertExercise(exerciseItem: ExerciseItem)

    //
    @Query("DELETE FROM exercise_table")
    suspend fun deleteAllExercises()
}