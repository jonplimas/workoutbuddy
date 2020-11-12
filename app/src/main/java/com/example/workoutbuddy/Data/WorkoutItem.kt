package com.example.workoutbuddy.Data

import androidx.annotation.NonNull
import androidx.room.*


@Entity(tableName = "workout_table")
data class WorkoutItem(
    val workoutImageResource: Int,
    @PrimaryKey(autoGenerate = true) @NonNull @ColumnInfo(name = "workoutID") val workoutID: Long,
    @NonNull @ColumnInfo(name = "workoutCreatorID") val workoutCreatorID: Long,
    @NonNull @ColumnInfo(name = "name") val name : String = " ",
    @NonNull @ColumnInfo(name = "category") val category : String? = null,
    @ColumnInfo(name = "description") val description : String? = null
)


@Entity(tableName = "exercise_table")
data class ExerciseItem(
    val imageResource: Int,
    @PrimaryKey(autoGenerate = true) @NonNull @ColumnInfo(name = "exerciseID") val exerciseID: Long,
    @NonNull @ColumnInfo(name = "name") val name: String,
    @NonNull @ColumnInfo(name = "type") val type: String? = null,
    @NonNull @ColumnInfo(name = "description") val description: String? = null
)


@Entity(tableName = "routine_table")
data class Routine(
    @PrimaryKey @NonNull @ColumnInfo(name = "routineID") val routineID: Long,
    @NonNull @ColumnInfo(name = "exID") val exID: Long,
    @NonNull @ColumnInfo(name = "name") val name: String,
    @NonNull @ColumnInfo(name = "type") val type: String,
    @NonNull @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "reps") val reps: Int?,
    @ColumnInfo(name = "sets") val sets: Int?,
    @ColumnInfo(name = "setQuantifier") val setQuantifier: String?
)



@Entity(tableName = "login_table")
data class Login(
    @PrimaryKey(autoGenerate = true) @NonNull @ColumnInfo(name = "loginID") val loginID: Long,
    @ColumnInfo(name = "loginName") val loginName: String,
    @ColumnInfo(name = "password") val loginpassword: String
)

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey @NonNull @ColumnInfo(name = "userID") val userID: Long,
    @ColumnInfo(name = "userName") val userName: String
)


@Entity(tableName = "badge_table")
data class Badge(
    @Ignore val imageResource: Int,
    @PrimaryKey(autoGenerate = true) @NonNull @ColumnInfo(name = "title") val title: String,
    @NonNull @ColumnInfo(name = "description") val description: String,
    @NonNull @ColumnInfo(name = "count") val count: Int = 0,
    @NonNull @ColumnInfo(name = "goal") val goal: Int?
)



//@Entity
//data class UserLoginAndUser (
//    @Embedded val login: Login,
//    @Relation(
//        parentColumn = "loginID",
//        entityColumn = "userID"
//    )
//    val user: User
//)






//@Entity(primaryKeys = ["workoutID", "exerciseID"])
//data class WorkoutExerciseCrossRef(
//    val workoutID: Int,
//    val exerciseID: Int
//)

//@Entity
//data class WorkoutWithExercises(
//    @Embedded val workout: WorkoutItem,
//    @Relation(
//        parentColumn = "workoutID",
//        entityColumn = "exerciseID",
//        associateBy = Junction(WorkoutExerciseCrossRef::class)
//    )
//    val exercises: List<ExerciseItem>?
//)

//@Entity
//data class UserWithWorkoutsAndExercises (
//    @Embedded val user: User,
//    @Relation(
//        entity = WorkoutItem::class,
//        parentColumn = "userID",
//        entityColumn = "userCreatorID"
//    )
//    val workouts: List<WorkoutWithExercises>
//)


