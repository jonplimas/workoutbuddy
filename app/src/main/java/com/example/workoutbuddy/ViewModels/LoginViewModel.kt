package com.example.workoutbuddy.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.workoutbuddy.Data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val loginRepository : LoginRepository

    val allLogins: LiveData<List<Login>>


    init {
        val loginDao = ExerciseRoomDatabase.getDatabase(
            application,
            viewModelScope
        ).loginDao()
        loginRepository =
            LoginRepository(loginDao)
        allLogins = loginRepository.allLogins

    }


    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insertLogin(login: Login) = viewModelScope.launch(Dispatchers.IO) {
        loginRepository.insertLogin(login)
    }


}