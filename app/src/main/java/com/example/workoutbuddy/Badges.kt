package com.example.workoutbuddy

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "badge_table")
data class Badge(
    val iconImage: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String
)