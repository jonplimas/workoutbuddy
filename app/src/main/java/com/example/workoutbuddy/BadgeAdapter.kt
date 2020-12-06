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
import android.widget.Toast
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

    internal fun setBadges(badges: List<Badge>) {
        this.badgeList = badges
        notifyDataSetChanged()
    }


    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // inflate individual Workout Card
        val badgeCardView: View = View.inflate(context, R.layout.achievement_item, null)

        // initialize items in relation to achievementBadge.xml
        val badgeImages: ImageView = badgeCardView.findViewById(R.id.achievementBadge)
        val badgeTitles: TextView = badgeCardView.findViewById(R.id.achName)

        // initialize individual Badges based on indices.
        val badge: Badge = badgeList[position]

        // fill workout card with data
        if(badge.count < badge.goal) {
            badgeImages.setImageResource(badge.lockImage)
        } else {
            badgeImages.setImageResource(badge.imageResource)
        }

        badgeTitles.text = badge.title


        badgeCardView.setOnClickListener {
            Toast.makeText(context, "${badgeList[position].title}: " + badgeList[position].description
                    + "\nCURRENT: ${badge.count} \nGOAL: ${badge.goal}", Toast.LENGTH_SHORT).show()
        }

        return badgeCardView
    }
}