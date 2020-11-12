package com.example.workoutbuddy

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.example.workoutbuddy.Data.Badge
import com.example.workoutbuddy.Data.WorkoutItem
import com.example.workoutbuddy.activities.WorkoutNav.StartWorkoutActivity

class BadgeAdapter(var context: Context, var badgeList: List<Badge>): BaseAdapter() {
    override fun getItem(position: Int): Any {
        return badgeList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return badgeList.size
    }

    internal fun setWorkouts(badges: List<Badge>) {
        this.badgeList = badges
        notifyDataSetChanged()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // inflate individual Workout Card
        val badgeCardView: View = View.inflate(context, R.layout.achievement_item, null)

        // initialize items in relation to workout_item.xml
        val badgeImages: ImageView = badgeCardView.findViewById(R.id.achievementBadge)
        val badgeTitles: TextView = badgeCardView.findViewById(R.id.achName)

        // initialize individual cards based on indices.
        val badge: Badge = badgeList[position]

        // fill workout card with data
        badgeImages.setImageResource(badge.imageResource)
        badgeTitles.text = badge.title


        badgeImages.setOnClickListener {
            //Toast.makeText(context, "Workout Description: " + workoutList[position].description, Toast.LENGTH_SHORT).show()
            val intent = Intent(context, StartWorkoutActivity::class.java)
            intent.putExtra("bName", badgeList[position].title)
            intent.putExtra("bDesc", badgeList[position].description)
            intent.putExtra("bCount", badgeList[position].count)
            intent.putExtra("bGoal", badgeList[position].goal)
            ActivityCompat.startActivity(context, intent, Bundle.EMPTY)
        }
        return badgeCardView
    }
}