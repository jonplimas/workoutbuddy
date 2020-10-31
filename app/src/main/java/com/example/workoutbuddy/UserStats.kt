package com.example.workoutbuddy

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "user_stats_table")
data class UserStats (
    @ColumnInfo(name = "userID")  val id: Int,
    @ColumnInfo(name = "totalWorkouts")var totalWorkouts: Int?,
    @ColumnInfo(name = "fullWorkouts")var fullWorkouts: Int?,
    @ColumnInfo(name = "upperWorkouts")var upperWorkouts: Int?,
    @ColumnInfo(name = "lowerWorkouts")var lowerWorkouts: Int?,
    @ColumnInfo(name = "coreWorkouts")var coreWorkouts: Int?
)