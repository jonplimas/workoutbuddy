package com.example.workoutbuddy.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.workoutbuddy.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(ExerciseItem::class, WorkoutItem::class, Badge::class), version = 7, exportSchema = false)
abstract class ExerciseRoomDatabase : RoomDatabase() {

    abstract fun exerciseDao(): ExerciseDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun badgeDao(): BadgeDao

    val MIGRATION_1_2 = object : Migration(1,2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // database.execSQL("CREATE TABLE IF NOT EXISTS routine_table (routineID VARCHAR(10) PRIMARY KEY NOT NULL, name varchar(100), type VARCHAR(30), description VARCHAR(100), reps INTEGER, sets INTEGER ) ")
            // database.execSQL("CREATE TABLE IF NOT EXISTS workout_table (name VARCHAR(30) PRIMARY KEY NOT NULL, category VARCHAR(30), description VARCHAR(100), FOREIGN KEY (routineID) REFERENCES routine_table(id))")
        }
    }

    private class ExerciseDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.exerciseDao(), database.workoutDao(), database.badgeDao())
                }
            }
        }


        suspend fun populateDatabase(exerciseDao: ExerciseDao, workoutDao:WorkoutDao, badgeDao:BadgeDao) {

            // Delete all content here.
            exerciseDao.deleteAllExercises()
            workoutDao.deleteAllWorkouts()
            badgeDao.deleteAllBadges()

            // Initilaize Exercises.
            // TO DO FOR ITERATION 2: UPDATE LIST OF EXERCISES
            var exercise = ExerciseItem(
                R.drawable.ic_baseline_image_24,
                1,
                "Push-Ups",
                "Chest/Back",
                "Plank position, press up"
            )
            exerciseDao.insertExercise(exercise)

            exercise = ExerciseItem(
                R.drawable.ic_baseline_favorite_24,
                2,
                "Pull-Ups",
                "Chest/Back",
                "Pull body up til chin reaches past bar"
            )
            exerciseDao.insertExercise(exercise)

            exercise = ExerciseItem(
                R.drawable.ic_baseline_fitness_center_24,
                3,
                "Wall Sits",
                "Quadriceps/Hamstring",
                "Hold a 90-degree squat against wall"
            )
            exerciseDao.insertExercise(exercise)

            exercise = ExerciseItem(
                R.drawable.ic_baseline_image_24,
                4,
                "Arm Circles",
                "Shoulders",
                "Lateral raise of arms and move in circular motion"
            )
            exerciseDao.insertExercise(exercise)

            exercise = ExerciseItem(
                R.drawable.ic_baseline_favorite_24,
                5,
                "Burpees",
                "Core",
                "push-up to jump squat"
            )
            exerciseDao.insertExercise(exercise)

            exercise = ExerciseItem(
                R.drawable.ic_baseline_fitness_center_24,
                6,
                "Plank",
                "Core",
                "hold bridge postion on elbows"
            )
            exerciseDao.insertExercise(exercise)

            exercise = ExerciseItem(
                R.drawable.ic_baseline_image_24,
                7,
                "Crunches",
                "Core",
                "sit up while lying on back "
            )
            exerciseDao.insertExercise(exercise)

            exercise = ExerciseItem(
                R.drawable.ic_baseline_favorite_24,
                8,
                "Bicep Curls",
                "Triceps/Biceps",
                "curl dumbbell while arms at sides"
            )
            exerciseDao.insertExercise(exercise)

            exercise = ExerciseItem(
                R.drawable.ic_baseline_fitness_center_24,
                9,
                "Skull Crushers",
                "Triceps/Biceps",
                "lying on bench, barbell tricep extension behind the head"
            )
            exerciseDao.insertExercise(exercise)

            exercise = ExerciseItem(
                R.drawable.ic_baseline_image_24,
                10,
                "Mountain Climbers",
                "Core",
                "knee drives in push-up position"
            )
            exerciseDao.insertExercise(exercise)



            // TODO: Add your own Workouts!
            var workout = WorkoutItem(
                R.drawable.ic_baseline_fitness_center_24, 1,0,
                "Ab Circuit", "Core", "Quick ab workout! Repeat every other day."
            )
            workoutDao.insertWorkout(workout)

            workout = WorkoutItem(
                R.drawable.ic_baseline_fitness_center_24, 2,0,
                "DB Arm Blaster", "Upper Body", "Quick arm workout with dumb bells! "
            )
            workoutDao.insertWorkout(workout)



            // TODO: Add your own Badges!
            var badge = Badge(
                imageResource = R.drawable.full_body1,
                title = "Feeling The Burn",
                description = "Complete 5 full body workouts",
                count = 0,
                goal = 5
            )
            badgeDao.insertBadge(badge)

            badge = Badge(
                imageResource = R.drawable.full_body2,
                title = "ON FIRE",
                description = "Complete 15 full body workouts ",
                count = 0,
                goal = 15
            )
            badgeDao.insertBadge(badge)

            badge = Badge(
                imageResource = R.drawable.full_body3,
                title = "#BuiltDifferent",
                description = "Complete 25 full body workouts",
                count = 0,
                goal = 25
            )
            badgeDao.insertBadge(badge)


            badge = Badge(
                imageResource = R.drawable.upper_body1,
                title = "Tickets to The Gun Show",
                description = "Reach 5 upper body workouts",
                count = 0,
                goal = 5
            )
            badgeDao.insertBadge(badge)

            badge = Badge(
                imageResource = R.drawable.upper_body2,
                title = "Holding Up the Weight of the World",
                description = "Reach 15 upper body workouts",
                count = 0,
                goal = 15
            )
            badgeDao.insertBadge(badge)

            badge = Badge(
                imageResource = R.drawable.upper_body3,
                title = "Terry Crew's Seal of Approval",
                description = "Reach 25 upper body workouts",
                count = 0,
                goal = 15
            )
            badgeDao.insertBadge(badge)


            badge = Badge(
                imageResource = R.drawable.core1,
                title = "Dad Bod No More" ,
                description = "Reach 5 core workouts",
                count = 0,
                goal = 5
            )
            badgeDao.insertBadge(badge)

            badge = Badge(
                imageResource = R.drawable.core2,
                title = "Beer Belly Goodbye",
                description = "Reach 15 core workouts",
                count = 0,
                goal = 15
            )
            badgeDao.insertBadge(badge)

            badge = Badge(
                imageResource = R.drawable.core3,
                title = "Is that a 6-Pack?!",
                description = "Reach 25 core workouts",
                count = 0,
                goal = 25
            )
            badgeDao.insertBadge(badge)

            badge = Badge(
                imageResource = R.drawable.lower_body1,
                title = "Leg Up on the Competition",
                description = " Reach 5 lower body workouts",
                count = 0,
                goal = 5
            )
            badgeDao.insertBadge(badge)

            badge = Badge(
                imageResource = R.drawable.lower_body2,
                title = "That Cake is not a Lie",
                description = "Reach 15 lower body workouts",
                count = 0,
                goal = 15
            )
            badgeDao.insertBadge(badge)

            badge = Badge(
                imageResource = R.drawable.lower_body3,
                title = "Never Skip Leg Day",
                description ="Reach 25 lower body workouts",
                count = 0,
                goal = 25
            )
            badgeDao.insertBadge(badge)


        }
    }



    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: ExerciseRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): ExerciseRoomDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExerciseRoomDatabase::class.java,
                    "exercise_database"
                )
                    .addCallback(
                        ExerciseDatabaseCallback(
                            scope
                        )
                    )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}