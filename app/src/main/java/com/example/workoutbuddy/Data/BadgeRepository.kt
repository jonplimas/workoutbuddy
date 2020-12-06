package com.example.workoutbuddy.Data

import androidx.lifecycle.LiveData

class BadgeRepository(private val badgeDao: BadgeDao) {

    val allBadges: LiveData<List<Badge>> = badgeDao.getAlphabetizedBadges()

    // suspend modifier tells the compiler that this needs to be called from a coroutine or another suspending function.
    suspend fun insertBadge(badge: Badge) {
        badgeDao.insertBadge(badge)
    }

    fun updateBadge(badge: Badge) {
        badgeDao.updateBadge(badge)
    }



}