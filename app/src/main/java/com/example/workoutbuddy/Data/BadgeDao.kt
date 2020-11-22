package com.example.workoutbuddy.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BadgeDao {
    @Query("SELECT * FROM badge_table")
    fun getAlphabetizedBadges(): LiveData<List<Badge>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBadge(badge: Badge)

    @Query("DELETE FROM badge_table")
    suspend fun deleteAllBadges()

    @Query("SELECT * FROM badge_table LIMIT 1")
    fun getAnyBadge(): Array<Badge?>?

}