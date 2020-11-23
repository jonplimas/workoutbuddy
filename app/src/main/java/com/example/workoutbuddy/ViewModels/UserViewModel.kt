package com.example.workoutbuddy.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.workoutbuddy.Data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : UserRepository

    val allUsers: LiveData<List<User>>


    init {
        val userDao = ExerciseRoomDatabase.getDatabase(
            application,
            viewModelScope
        ).userDao()
        repository =
            UserRepository(userDao)
        allUsers = repository.allUsers

    }


    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insertUser(user : User) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertUser(user)
    }


}