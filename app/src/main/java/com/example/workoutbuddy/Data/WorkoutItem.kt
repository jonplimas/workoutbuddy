package com.example.workoutbuddy.Data

import androidx.annotation.NonNull
import androidx.room.*
import com.example.workoutbuddy.R


@Entity(tableName = "workout_table")
data class WorkoutItem(
    val workoutImageResource: Int,
    @NonNull @ColumnInfo(name = "workoutCreatorID") val workoutCreatorID: Int,
    @NonNull @ColumnInfo(name = "name") val name : String = " ",
    @NonNull @ColumnInfo(name = "category") val category : String? = null,
    @ColumnInfo(name = "description") val description : String? = null
) {
    @PrimaryKey(autoGenerate = true) @NonNull @ColumnInfo(name = "workoutID") var workoutID: Int = 0
}


@Entity(tableName = "exercise_table")
data class ExerciseItem(
    val imageResource: Int,
    @NonNull @ColumnInfo(name = "name") var name: String,
    @NonNull @ColumnInfo(name = "type") var type: String? = null,
    @NonNull @ColumnInfo(name = "description") var description: String? = null,
    @ColumnInfo(name = "reps") var reps: String? = "",
    @ColumnInfo(name = "sets") var sets: String? = "",
    @ColumnInfo(name = "setQuantifier") var setQuantifier: String? = ""

) {
    @PrimaryKey(autoGenerate = true) @NonNull @ColumnInfo(name = "exerciseID ") var exerciseID: Int = 0
}


@Entity(tableName = "routine_table")
data class Routine(
    @NonNull @ColumnInfo(name = "exID") var exID: Int,
    @NonNull @ColumnInfo(name = "name") var name: String,
    @NonNull @ColumnInfo(name = "type") var type: String,
    @NonNull @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "reps") var reps: Int?,
    @ColumnInfo(name = "sets") var sets: Int?,
    @ColumnInfo(name = "setQuantifier") var setQuantifier: String?
) {
    @PrimaryKey @NonNull @ColumnInfo(name = "routineID") var routineID: Int = 0
}


@Entity(tableName = "login_table")
data class Login(
    @ColumnInfo(name = "loginName") val loginName: String,
    @ColumnInfo(name = "password") val loginPassword: String
) {
    @PrimaryKey(autoGenerate = true) @NonNull @ColumnInfo(name = "loginID") var loginID: Int = 0
}


@Entity(tableName = "user_table")
data class User(
    @ColumnInfo(name = "userName") var userName: String,
    //User Metrics
    @ColumnInfo(name = "totalCount") var totolCount: Int = 0,
    @ColumnInfo(name = "fullCount") var fullCount: Int = 0,
    @ColumnInfo(name = "upperCount") var upperCount: Int = 0,
    @ColumnInfo(name = "lowerCount") var lowerCount: Int = 0,
    @ColumnInfo(name = "coreCount") var coreCount: Int = 0
) {
    @PrimaryKey(autoGenerate = true) @NonNull @ColumnInfo(name = "userID") var userID: Int = 0
}


@Entity(tableName = "badge_table")
data class Badge(
    val imageResource: Int,
    @PrimaryKey @NonNull @ColumnInfo(name = "title") val title: String,
    @NonNull @ColumnInfo(name = "description") var description: String,
    @NonNull @ColumnInfo(name = "count") var count: Int = 0,
    @NonNull @ColumnInfo(name = "goal") val goal: Int
) {
    var lockImage = R.drawable.ic_baseline_lock_24
}



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