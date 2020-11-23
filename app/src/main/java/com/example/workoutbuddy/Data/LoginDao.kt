package com.example.workoutbuddy.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LoginDao {

    @Query("SELECT * FROM login_table")
    fun getLogins(): LiveData<List<Login>>

    // ConflictStrategy: ignores new word if it has the same name as one already in the list
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLogin(login: Login)

    @Query("DELETE FROM login_table")
    suspend fun deleteAllLogins()

}