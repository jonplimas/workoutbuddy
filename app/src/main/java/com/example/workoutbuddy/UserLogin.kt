package com.example.workoutbuddy

import androidx.annotation.NonNull
import androidx.room.*

@Entity(tableName = "user_login_table")
data class UserLogin (
    @PrimaryKey @NonNull @ColumnInfo(name = "loginID") val loginID: Int,
    @ColumnInfo(name = "loginName") val loginName: String,
    @ColumnInfo(name = "password") val loginpassword: String
)

@Entity(tableName = "user_table")
data class User (
    @PrimaryKey @NonNull @ColumnInfo(name = "userID") val userID: Int,
    @ColumnInfo(name = "userName") val userName: String,
    @ColumnInfo(name = "firstName") val firstName: String,
    @ColumnInfo(name = "favExercises") val favExercises: List<ExerciseItem>,
    @ColumnInfo(name = "favWorkouts") val favWorkouts: List<WorkoutItem>
)

data class UserLoginAndUser (
    @Embedded val login: UserLogin,
    @Relation(
        parentColumn = "loginID",
        entityColumn = "userID"
    )
    val user: User
)
