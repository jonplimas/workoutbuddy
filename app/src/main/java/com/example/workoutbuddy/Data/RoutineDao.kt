package com.example.workoutbuddy.Data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface RoutineDao {

    // Retrieves the entire list of Routines in ABC order
    // Note: LiveData allows data to be observable and live changes can be made to UI upon db updates.
    @Query("SELECT * from routine_table")
    fun getRoutines(): LiveData<List<Routine>>

    @Query("SELECT * from routine_table WHERE type = 'Core' ORDER BY name ASC")
    fun getCoreRoutines(): LiveData<List<Routine>>

    @Query("SELECT * from routine_table WHERE exID = :workoutID")
    fun getRoutinesByWorkoutID(workoutID: String): LiveData<List<Routine>>


    // ConflictStrategy: ignores new word if it has the same name as one already in the list
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRoutine(routine: Routine)


    @Query("DELETE FROM routine_table")
    suspend fun deleteAllRoutines()

    @Query("SELECT * FROM routine_table LIMIT 1")
    fun getAnyRoutine(): Array<Routine?>?

    @Delete
    fun deleteRoutine(routine: Routine?)

    @Query("DELETE FROM routine_table WHERE exID =:workoutID")
    fun deleteRoutinesByWorkoutID(workoutID: Int)


}
