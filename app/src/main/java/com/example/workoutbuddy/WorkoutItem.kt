package com.example.workoutbuddy

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_table")
data class WorkoutItem(
    @PrimaryKey @NonNull @ColumnInfo(name = "name") val name : String = " ",
    @ColumnInfo(name = "category") val category : String? = null,
    @ColumnInfo(name = "duration")val duration : Int? = null,
    @ColumnInfo(name = "exercises") var excercises : List<ExerciseItem>? = null,
    @ColumnInfo(name = "reps") var reps : Int? = null,
    @ColumnInfo(name = "sets") var sets : Int? = null
)