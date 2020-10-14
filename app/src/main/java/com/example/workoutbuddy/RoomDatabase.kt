package com.example.workoutbuddy

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(ExerciseItem::class), version = 1, exportSchema = false)
abstract class ExerciseRoomDatabase : RoomDatabase() {

    abstract fun exerciseDao(): ExerciseDao

    private class ExerciseDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.exerciseDao())
                }
            }
        }

        suspend fun populateDatabase(exerciseDao: ExerciseDao) {

            // Delete all content here.
            exerciseDao.deleteAllExercises()

            // Initilaize Exercises.
            // TO DO FOR ITERATION 2: UPDATE LIST OF EXERCISES
            var exercise = ExerciseItem(R.drawable.ic_baseline_image_24, "Push-Ups", "Chest", "Plank position, press up")
            exerciseDao.insertExercise(exercise)

            exercise =ExerciseItem(R.drawable.ic_baseline_favorite_24, "Pull-Ups", "Back", "Pull body up til chin reaches past bar")
            exerciseDao.insertExercise(exercise)

            exercise =  ExerciseItem(R.drawable.ic_baseline_fitness_center_24, "Wall Sits", "Quadriceps", "Hold a 90-degree squat against wall")
            exerciseDao.insertExercise(exercise)

            exercise = ExerciseItem(R.drawable.ic_baseline_image_24, "Arm Circles", "Shoulders", "Lateral raise of arms and move in circular motion")
            exerciseDao.insertExercise(exercise)

            exercise = ExerciseItem(R.drawable.ic_baseline_favorite_24, "Burpees", "Core", "push-up to jump squat")
            exerciseDao.insertExercise(exercise)

            exercise =  ExerciseItem(R.drawable.ic_baseline_fitness_center_24, "Plank", "Core", "hold bridge postion on elbows")
            exerciseDao.insertExercise(exercise)

            exercise = ExerciseItem(R.drawable.ic_baseline_image_24,"Crunches", "Core", "sit up while lying on back ")
            exerciseDao.insertExercise(exercise)

            exercise = ExerciseItem(R.drawable.ic_baseline_favorite_24, "Bicep Curls", "Biceps", "curl dumbbell while arms at sides")
            exerciseDao.insertExercise(exercise)

            exercise = ExerciseItem(R.drawable.ic_baseline_fitness_center_24,"Skull Crushers", "Triceps", "lying on bench, barbell tricep extension behind the head")
            exerciseDao.insertExercise(exercise)

            exercise = ExerciseItem(R.drawable.ic_baseline_image_24, "Mountain Climbers", "Core", "knee drives in push-up position")
            exerciseDao.insertExercise(exercise)

            // TODO: Add your own words!
        }
    }



    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: ExerciseRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): ExerciseRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExerciseRoomDatabase::class.java,
                    "exercise_database"
                )
                    .addCallback(ExerciseDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}