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
@Database(entities = arrayOf(ExerciseItem::class, WorkoutItem::class, Badge::class), version = 9, exportSchema = false)
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
                "Target Muscles: pectorals, deltoids, triceps, abdominals, serratus\n\n" +
                        "1. Get on the floor on all four with hands near your shoulders\n" +
                        "2. Straighten your arms and legs, pushing your body off the floor\n" +
                        "3. Lower your body until ur chest nearly touches the floor\n" +
                        "4. Pause, then push yourself back up\n" +
                        "5. Repeat for every rep\n"
            )
            exerciseDao.insertExercise(exercise)

            exercise = ExerciseItem(
                R.drawable.ic_baseline_favorite_24,
                2,
                "Pull-Ups",
                "Chest/Back",
                "Target Muscles: latissimus, trapezius, biceps, triceps, deltoids, pectorals\n" +
                        "(Requires pull-up bar or equivalent)\n\n" +
                        "1. Grab the pull-up bar with ur palms down\n" +
                        "2. Hang on the pull-up bar with your arms straight and legs off the floor\n" +
                        "3. Pull yourself up until your chin passes the pull-up bar\n" +
                        "4. Pause, then lower yourself back down\n" +
                        "5. Repeat for every rep\n"
            )
            exerciseDao.insertExercise(exercise)

            exercise = ExerciseItem(
                R.drawable.ic_baseline_fitness_center_24,
                3,
                "Wall Sits",
                "Quadriceps/Hamstring",
                "Target Muscles: glutes, calves, quads, hamstrings\n\n" +
                        "1. Stand with your back pressing against a wall\n" +
                        "2. Slide your feet forward while sliding ur back against the wall\n" +
                        "3. Hold that position, as if sitting on an invisible chair, for 60 seconds\n"
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
                "Target Muscles: quads, calves, glutes, hamstring, abdominals, triceps, deltoids\n\n" +
                        "1. Bend your knees and straighten your back, forming a squat position\n" +
                        "2. Lower your hands to the floor directly in front of yourself\n" +
                        "3. Jump back with your feet and land on the balls of your feet, forming a plank position\n" +
                        "4. Jump forward with your feet so that they land just outside of your hands\n" +
                        "5. With your arms, reach over your head and jump up into the air\n" +
                        "5. Land the jump and repeat for every rep\n"
            )
            exerciseDao.insertExercise(exercise)

            exercise = ExerciseItem(
                R.drawable.ic_baseline_fitness_center_24,
                6,
                "Plank",
                "Core",
                "Target Muscles: abdominals, triceps, obliques, latissimus, glutes\n\n" +
                        "1. Get on the floor on all four and place your hands slightly wider than your shoulders\n" +
                        "2. Straighten your arms and legs, forming a plank position\n" +
                        "3. Hold position for 60 seconds (or as long as you can)\n"
            )
            exerciseDao.insertExercise(exercise)

            exercise = ExerciseItem(
                R.drawable.ic_baseline_image_24,
                7,
                "Crunches",
                "Core",
                "Target Muscles: abdominal, obliques\n\n" +
                        "1. Lie down on your back with your hands behind your head\n" +
                        "2. Firmly plant your feet on the floor with knees bent\n" +
                        "3. Lift your upper body up and forward so that your head and shoulders lifts off the floor\n" +
                        "4. Pause, then lower yourself back down onto the floor\n" +
                        "5. Repeat for every rep\n"
            )
            exerciseDao.insertExercise(exercise)

            exercise = ExerciseItem(
                R.drawable.ic_baseline_favorite_24,
                8,
                "Bicep Curls",
                "Triceps/Biceps",
                "(requires dumbbells or barbell)\n\n" +
                        "1. Stand with your feet shoulder-width apart\n" +
                        "2. Grab a pair of dumbbells, or a barbell, with palms facing forward and hanging at your sides\n" +
                        "3. Curl both arms upward until they're in front of your shoulders, carrying the weight\n" +
                        "4. Pause, then slowly lower the weight back down\n" +
                        "5. Repeat for every rep\n"
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
                "Target Muscles: deltoids, triceps, obliques, abdominals, quads, hamstrings\n\n" +
                        "1. Get on the floor on all four with your hands stack directly under shoulders\n" +
                        "2. Straighten your arms and legs, forming a plank position\n" +
                        "3. Pull one of your knees towards your chest without it dragging on the floor\n" +
                        "4. Return to plank position and pull your other knee towards your chest\n" +
                        "5. Repeat for every rep, alternating legs\n"
            )
            exerciseDao.insertExercise(exercise)

            exercise = ExerciseItem(
                exerciseID = 11,
                imageResource = R.drawable.ic_baseline_favorite_24,
                name = "Bridges",
                type = "Glutes",
                description = "Target Muscles: glutes, hamstrings\n\n" +
                    "1. Lie down on your back with your hands straight out at your sides\n" +
                    "2. Firmly plant your feet on the floor with knees bent\n" +
                    "3. Raise your hips off the floor, creating a straight line form your knees to shoulders\n" +
                    "4. Pause, then lower your hips back onto the floor\n" +
                    "5. Repeat for every rep\n"
            )
            exerciseDao.insertExercise(exercise)

            exercise = ExerciseItem(
                    exerciseID = 12,
            imageResource = R.drawable.ic_baseline_fitness_center_24,
            name = "Step-Ups",
            type = "Quadriceps/Hamstrings",
            description = "Target Muscles: glutes, quads, calves, hamstrings, soleus\n" +
                    "(requires step/chair/bench or equivalent)\n\n" +
                    "1. Stand in front of your step/bench\n" +
                    "2. Lift one of your legs and place your foot onto the step\n" +
                    "3. Lift your other leg and place your other foot onto the step, standing on the step\n" +
                    "4. Step back onto the floor, 1 foot at a time\n" +
                    "5. Repeat for every rep\n"
            )
            exerciseDao.insertExercise(exercise)

            exercise = ExerciseItem(
                exerciseID = 13 ,
                imageResource = R.drawable.ic_baseline_fitness_center_24,
                name = "Lunges",
                type = "Quadriceps/Hamstrings",
                description = "Target Muscles: glutes, quads, calves, latissimus, trapezius, rhomboids, hamstrings\n\n" +
                        "1. Stand with feet shoulder-width apart\n" +
                        "2. Take a big step forward with 1 leg and shift your weight onto that leg\n" +
                        "3. Lower your body until ur back knee nearly touches the floor\n" +
                        "4. Lift your body back up and step back, standing straight\n" +
                        "5. Alternate legs and repeat for every rep\n"
            )
            exerciseDao.insertExercise(exercise)


            exercise = ExerciseItem(
                exerciseID = 14,
                imageResource = R.drawable.ic_baseline_fitness_center_24,
                name = "Squats",
                type = "Quadriceps/Hamstrings",
                description = "Target Muscles: quads, hamstrings, glutes, abdominals, calves\n\n" +
                        "1. Stand straight with feet shoulder-width apart\n" +
                        "2. While firmly plant your feet onto the ground, bend your knees and lower ur body down\n" +
                        "3. Pause and hold that position, as if sitting on an invisible chair\n" +
                        "4. Raise ur body back up\n" +
                        "5. Repeat for every rep\n"
            )
            exerciseDao.insertExercise(exercise)

            exercise = ExerciseItem(
                exerciseID =  15,
                imageResource = R.drawable.ic_baseline_fitness_center_24,
                name = "Single-leg Squats",
                type = "Quadriceps/Hamstrings",
                description = "Target Muscles: quads, hamstrings, glutes, abdominals, calves.\n\n" +
                        "1. Stand on 1 leg, your lifted foot pointing forward\n" +
                        "2. Bend the leg your standing on while keeping your lifted leg off the ground\n" +
                        "3. Pause holding that position, as if sitting on an invisible chair\n" +
                        "4. Raise your body back up\n" +
                        "5. Alternate legs and repeat for every rep\n"
            )
            exerciseDao.insertExercise(exercise)

            exercise = ExerciseItem(
                exerciseID =  16,
                imageResource = R.drawable.ic_baseline_image_24,
                name = "Forearm Planks",
                type = "Core",
                description = "Target Muscles: abdominals, triceps, obliques, latissimus, glutes\n\n" +
                        "1. Get on the floor on all four but bend your arms so your weight rests on your forearms\n" +
                        "2. Straighten out your legs and back\n" +
                        "3. Hold position for 50 seconds (or as long as you can)\n"
            )
            exerciseDao.insertExercise(exercise)


            exercise = ExerciseItem(
                exerciseID =  17,
                imageResource = R.drawable.ic_baseline_image_24,
                name = "Side Planks",
                type = "Core",
                description = "Target Muscles: abdominals, glutes, obliques, abductors, triceps\n\n" +
                        "1. Lie on your side, legs extended and stacked from hip to feet\n" +
                        "2. Lift your body off the ground with your arm, resting your weight onto your forearm\n" +
                        "3. Hold position for 60 seconds (of as long as you can)\n" +
                        "3. Alternate to your other side and repeat once more\n"
            )
            exerciseDao.insertExercise(exercise)


            exercise = ExerciseItem(
                exerciseID = 18 ,
                imageResource = R.drawable.ic_baseline_image_24,
                name = "Calf Raises",
                type = "Calves",
                description = "Target Muscles: calves, tibialis posterior, soleus \n\n" +
                        "1. Stand straight with your feet shoulder-width apart\n" +
                        "2. Raise your heels, standing on your toes and balls of your feet\n" +
                        "3. Pause, then lower your heels back down onto the floor\n" +
                        "4. Repeat for every rep\n"
            )
            exerciseDao.insertExercise(exercise)


            exercise = ExerciseItem(
                exerciseID = 19 ,
                imageResource = R.drawable.ic_baseline_image_24,
                name = "Donkey Kicks",
                type = "Glutes",
                description = "Target Muscles: glutes, core, hamstrings\n\n" +
                        "1. Get on the floor on all four and place your hands and knees under the shoulders and hips, respectively\n" +
                        "2. With one of your legs, kick back, fully extending your leg out slowly\n" +
                        "3. Pause, then bring back the leg by bending at the knee\n" +
                        "4. Alternate legs and repeat for every rep\n"
            )
            exerciseDao.insertExercise(exercise)


            exercise = ExerciseItem(
                exerciseID = 20 ,
                imageResource = R.drawable.ic_baseline_image_24,
                name = "Bicycle Crunches",
                type = "Core",
                description = "1. Lie down on your back with your hands behind your head\n" +
                        "2. Firmly plant your feet on the floor with knees bent\n" +
                        "3. Lift one of your shoulder blades and the opposite knee off the ground and curling into your chest\n" +
                        "4. Bring your elbow and knee towards each other\n" +
                        "5. Pause, then lay back down and alternate arm and leg\n" +
                        "6. Repeat for every rep\n"
            )
            exerciseDao.insertExercise(exercise)

            exercise = ExerciseItem(
                exerciseID = 21 ,
                imageResource = R.drawable.ic_baseline_image_24,
                name = "Reverse Crunches",
                type = "Core",
                description = ". Lie down on your back with your hands straight out at your sides \n" +
                        "2. Bent your knees and plant your feet flat on the floor\n" +
                        "3. lift your legs and pull your knees towards your chest\n" +
                        "4. As your knees come level with your gaze, push them up towards the ceiling, lifting your lower back\n" +
                        "5. Pause, then lower your hips back onto the floor, bending your knees back down till your feet land on the floor\n" +
                        "6. Repeat for every rep\n"
            )
            exerciseDao.insertExercise(exercise)

            exercise = ExerciseItem(
                exerciseID = 22 ,
                imageResource = R.drawable.ic_baseline_image_24,
                name = "Jump Rope",
                type = "Calves",
                description = "(requires jump rope or equivalent)\n\n" +
                        "1. Grab the handles/edges of the jump rope and start with the rope behind you, resting at your heels\n" +
                        "2. Rotate your forearms forward then your wrist, allowing the jump rope to swing over your head\n" +
                        "3. As the rope swings up overhead, bend your knees slightly, jumping as the rope swings in front of you\n" +
                        "4. Continue rotating the jump rope, successfully jumping over the rope consecutively for 5 minutes\n"
            )
            exerciseDao.insertExercise(exercise)

            exercise = ExerciseItem(
                exerciseID = 23 ,
                imageResource = R.drawable.ic_baseline_image_24,
                name = "Lateral Raises",
                type = "Shoulders",
                description = "(require dumbbells or kettlebells)\n\n" +
                        "1. stand with feet shoulder-width apart\n" +
                        "2. grab a pair of dumbbells with palms facing inward and hanging at your sides\n" +
                        "3. Raise your arms out to the side until they're at shoulder level, forming a T shape with your body\n" +
                        "4. pause, then slowly lower the weights back to your sides\n" +
                        "5. repeat for every rep\n"
            )
            exerciseDao.insertExercise(exercise)


            exercise = ExerciseItem(
                exerciseID = 24 ,
                imageResource = R.drawable.ic_baseline_image_24,
                name = "Overhead Press",
                type = "Shoulders",
                description = "require dumbbells or barbell\n\n" +
                        "1. Stand with your feet shoulder-width apart\n" +
                        "2. Grab a pair of dumbbells or a barbell and lift it by your shoulders with an overhand grip\n" +
                        "3. Raise the weight above your head in a straight upward motion till your hands are fully extended out\n" +
                        "4. Pause, then return the dumbbells or barbell down to your shoulders\n" +
                        "5. Repeat for every rep\n"
            )
            exerciseDao.insertExercise(exercise)

            exercise = ExerciseItem(
                exerciseID = 25 ,
                imageResource = R.drawable.ic_baseline_image_24,
                name = "Dumbbell Squats",
                type = "Glutes",
                description = "(Requires dumbbells)\n\n" +
                        "1. Stand with your feet shoulder-width apart\n" +
                        "2. Grab a pair of dumbbells with palms facing inward and hanging at your sides\n" +
                        "3. While firmly planting your feet to the ground, bend your knees and lower ur body down\n" +
                        "4. Pause and hold that position, as if sitting on an invisible chair\n" +
                        "5. Raise ur body back up\n" +
                        "6. Repeat for every rep\n"
            )
            exerciseDao.insertExercise(exercise)

            exercise = ExerciseItem(
                exerciseID = 26 ,
                imageResource = R.drawable.ic_baseline_image_24,
                name = "Dumbbell Lunges",
                type = "Quadriceps/Hamstrings",
                description = "require dumbbells\n\n" +
                        "1. Stand with your feet shoulder-width apart\n" +
                        "2. Grab a pair of dumbbells with palms facing inward and hanging at your sides\n" +
                        "3. Take a big step forward with 1 leg and shift your weight onto that leg\n" +
                        "4. Lower your body until ur back knee nearly touches the floor\n" +
                        "5. Lift your body back up and step back, standing straight up\n" +
                        "6. Alternate legs and repeat for every rep\n"
            )
            exerciseDao.insertExercise(exercise)

            exercise = ExerciseItem(
                exerciseID = 27 ,
                imageResource = R.drawable.ic_baseline_image_24,
                name = "Bent-over Rows",
                type = "Chest/Back",
                description = "Target Muscles: latissimus, trapezius, rhomboids, deltoids, forearms\n" +
                        "(require dumbbells or barbell)\n\n" +
                        "1. Stand with your feet bent and shoulder-width apart\n" +
                        "2. Grab a pair of dumbbells or a barbell with an overhand grip\n" +
                        "3. Bend over at the waist with your arms hanging down and back straight\n" +
                        "4. Pull the weight towards your lower chest by bending your elbows and pulling up\n" +
                        "5. Pause, then slowly lower the weight back down\n" +
                        "6. Repeat for every rep\n"
            )
            exerciseDao.insertExercise(exercise)

            exercise = ExerciseItem(
                exerciseID = 28 ,
                imageResource = R.drawable.ic_baseline_image_24,
                name = "Barbell Deadlift",
                type = "Quadriceps/Hamstrings",
                description = "1. Stand with feet shoulder-width apart, in front of a barbell\n" +
                        "2. Bend over and grab the bar with a shoulder-width grip\n" +
                        "3. Bend your knees until your shins touch the bar\n" +
                        "4. Lift your chest up and stand up while carrying the barbell\n" +
                        "5. Set the barbell back down slowly\n" +
                        "6. Repeat for every rep \n"
            )
            exerciseDao.insertExercise(exercise)
            exercise = ExerciseItem(
                exerciseID = 29 ,
                imageResource = R.drawable.ic_baseline_image_24,
                name = "Bench Press",
                type = "Chest/Back",
                description = "(require dumbbell or barbell)\n\n" +
                        "1. Lie on a flat bench facing up with either dumbbells in hand or under a lifted barbell\n" +
                        "2. Grab the barbell or dumbbells and hold it by your chest\n" +
                        "3. Press the weight up till your arms are extended straight up\n" +
                        "4. Pause, then slowly lower the weight back down towards your chest\n" +
                        "5. Repeat for every rep\n"
            )
            exerciseDao.insertExercise(exercise)

            exercise = ExerciseItem(
                exerciseID = 30 ,
                imageResource = R.drawable.ic_baseline_image_24,
                name = "Kettlebell Swings",
                type = "Shoulders",
                description = "Target Muscles:  gluteal, abdominals, latissimus, hamstrings, deltoids\n" +
                        "(Require kettlebell)\n\n" +
                        "1. Stand with your feet bent and shoulder-width apart\n" +
                        "2. Firmly grab a kettlebell with both hands and hold it in front of your thighs\n" +
                        "3. Swing the kettlebell forward till your arms extend straight in front of you\n" +
                        "4. Pause, then let it swing back down between your thighs\n" +
                        "5. Repeat for every rep\n"
            )
            exerciseDao.insertExercise(exercise)

            exercise = ExerciseItem(
                exerciseID = 31,
                imageResource = R.drawable.ic_baseline_image_24,
                name = "Thrusters",
                type = "Quads/Hamstrings",
                description = "requires dumbbells, kettlebells, or barbell\n\n" +
                        "1. Stand with your feet shoulder-width apart\n" +
                        "2. Grab a barbell, or pair of dumbbells/kettlebells, using an underhand grip and bring the weight by your shoulders\n" +
                        "3. Start by bending your knees, forming a squat position while holding the weight by your shoulders\n" +
                        "4. Slowly stand straight up while holding the weight by your shoulders\n" +
                        "5. Once standing straight, raise the weight over your head\n" +
                        "6. Pause, then bring the weight back down to your shoulders\n" +
                        "7. Repeat for every rep\n"
            )
            exerciseDao.insertExercise(exercise)

            exercise = ExerciseItem(
                exerciseID = 32,
                imageResource = R.drawable.ic_baseline_image_24,
                name = "Shoulder press",
                type = "Shoulders",
                description = "(Requires dumbbells or barbell)\n\n" +
                        "1. Stand with your feet shoulder-width apart\n" +
                        "2. Grab a barbell, or pair of dumbbells, using an underhand grip and bring the weight by your shoulders\n" +
                        "3. Extend your arms up, raising the weight over your head\n" +
                        "4. Pause, then bring the weight back down to your shoulders\n" +
                        "5. Repeat for every rep\n"
            )
            exerciseDao.insertExercise(exercise)


            exercise = ExerciseItem(
                exerciseID = 33,
                imageResource = R.drawable.ic_baseline_image_24,
                name = "Single-Arm Deadlift",
                type = "Quadriceps/Hamstrings",
                description = "Target Muscles: glutes, hamstrings, erector spinae, soleus, quads\n" +
                        "(Require dumbbell or kettlebell)\n\n" +
                        "1. Stand with your feet shoulder-width apart\n" +
                        "2. Place your shin up against a barbell or kettlebell\n" +
                        "3. Bend your knees and grab the weight with one arm using an overhand grip\n" +
                        "4. Slowly stand straight up, pulling the weight up along with you\n" +
                        "5. Pause, then set the weight back down slowly\n" +
                        "6. Repeat for every rep\n"
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

            workout = WorkoutItem(
                workoutImageResource = R.drawable.ic_baseline_fitness_center_24,
                workoutID = 3,
                workoutCreatorID = 0,
                name = "Upper Calisthenics",
                category = "Upper Body",
                description = "Chest and core workout, no equipment needed."
            )
            workoutDao.insertWorkout(workout)

            workout = WorkoutItem(
                workoutImageResource = R.drawable.ic_baseline_fitness_center_24,
                workoutID = 4,
                workoutCreatorID = 0,
                name = "Chest Day with Abs",
                category = "Upper Body",
                description = "Chest and core workout at the gym. Some equipment needed"
            )
            workoutDao.insertWorkout(workout)

            workout = WorkoutItem(
                workoutImageResource = R.drawable.ic_baseline_fitness_center_24,
                workoutID = 5,
                workoutCreatorID = 0,
                name = "Oh Dreaded Leg Day",
                category = "Lower Body",
                description = "Leg day calisthenics, no equipment needed."
            )
            workoutDao.insertWorkout(workout)


            workout = WorkoutItem(
                workoutImageResource = R.drawable.ic_baseline_fitness_center_24,
                workoutID = 6,
                workoutCreatorID = 0,
                name = "Leg Day Deluxe",
                category = "Lower Body",
                description = "Typical leg day at the gym. Equipment needed."
            )
            workoutDao.insertWorkout(workout)


            workout = WorkoutItem(
                workoutImageResource = R.drawable.ic_baseline_fitness_center_24,
                workoutID = 7,
                workoutCreatorID = 0,
                name = "DB Arm Blaster",
                category = "Upper Body",
                description = "massive arm pump with dumbbells"
            )
            workoutDao.insertWorkout(workout)


            workout = WorkoutItem(
                workoutImageResource = R.drawable.ic_baseline_fitness_center_24,
                workoutID = 8,
                workoutCreatorID = 0,
                name = "Cannons for Arms",
                category = "Upper Body",
                description = "Equipment required, sleeves optional."
            )
            workoutDao.insertWorkout(workout)


            workout = WorkoutItem(
                workoutImageResource = R.drawable.ic_baseline_fitness_center_24,
                workoutID = 9,
                workoutCreatorID = 0,
                name = "Quarantine Circuit",
                category = "Full Body",
                description = "Stay at home routine. Wholesome calisthenics, no equipment required."
            )
            workoutDao.insertWorkout(workout)



            workout = WorkoutItem(
                workoutImageResource = R.drawable.ic_baseline_fitness_center_24,
                workoutID = 10,
                workoutCreatorID = 0,
                name = "The Works",
                category = "Full Body",
                description = "Equipment required. A little bit of everything to get the blood rushing!"
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