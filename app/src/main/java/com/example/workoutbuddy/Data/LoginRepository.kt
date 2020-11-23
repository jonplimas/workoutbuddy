package com.example.workoutbuddy.Data

class LoginRepository(private val loginDao: LoginDao) {

    val allLogins = loginDao.getLogins()

    // suspend modifier tells the compiler that this needs to be called from a coroutine or another suspending function.
    suspend fun insertLogin(login: Login) {
        loginDao.insertLogin(login)
    }
}