package com.example.workoutbuddy.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.workoutbuddy.Data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BadgeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: BadgeRepository
    // Using LiveData and caching what our DAO returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allBadges: LiveData<List<Badge>>


    init {
        val badgeeDao = ExerciseRoomDatabase.getDatabase(
            application,
            viewModelScope
        ).badgeDao()
        repository =
            BadgeRepository(badgeeDao)
        allBadges = repository.allBadges

    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insertBadge(badge: Badge) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertBadge(badge)
    }

    fun updateBadge(badge: Badge) = viewModelScope.launch(Dispatchers.IO){
        repository.updateBadge(badge)
    }

}