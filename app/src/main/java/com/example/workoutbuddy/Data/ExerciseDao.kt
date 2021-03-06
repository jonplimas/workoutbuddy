package com.example.workoutbuddy.Data

import androidx.lifecycle.LiveData
import androidx.room.*


// In the DAO (data access object), you specify SQL queries and associate them with method calls.
// The compiler checks the SQL and generates queries from convenience annotations for common queries.
@Dao
interface ExerciseDao {

    // Retrieves the entire list of Exercise in ABC order
    // Note: LiveData allows data to be observable and live changes can be made to UI upon db updates.
    @Query("SELECT * from exercise_table ORDER BY name ASC")
    fun getAlphabetizedExercises(): LiveData<List<ExerciseItem>>

    @Query("SELECT * from exercise_table WHERE type LIKE '%Core%' ORDER BY name ASC")
    fun getCoreExercises(): LiveData<List<ExerciseItem>>

    @Query("SELECT * from exercise_table WHERE type LIKE '%Chest/Back%' OR type LIKE '%Shoulders%' OR type LIKE '%Triceps/Biceps%' ORDER BY name ASC")
    fun getUpperExercises(): LiveData<List<ExerciseItem>>

    @Query("SELECT * from exercise_table WHERE type LIKE '%Glutes%' OR type LIKE '%Quads/Hams%' OR type LIKE '%Calves%' ORDER BY name ASC")
    fun getLowerExercises(): LiveData<List<ExerciseItem>>

    // ConflictStrategy: ignores new word if it has the same name as one already in the list
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertExercise(exerciseItem: ExerciseItem)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRoutine(routine: Routine)

    //
    @Query("DELETE FROM exercise_table")
    suspend fun deleteAllExercises()

    @Query("SELECT * FROM exercise_table LIMIT 1")
    fun getAnyExercise(): Array<ExerciseItem?>?

    @Delete
    fun deleteExercise(exerciseItem: ExerciseItem?)


//    @Transaction
//    @Query("SELECT * from workout_table")
//    fun getWorkoutWithExercises(): LiveData<List<WorkoutWithExercises>>

//    @Transaction
//    @Query("SELECT * from user_table")
//    fun getUserWithWorkoutsAndExercises(): LiveData<List<UserWithWorkoutsAndExercises>>









}