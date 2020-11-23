package com.example.workoutbuddy.Data

class UserRepository(private val userDao: UserDao) {

    val allUsers = userDao.getUsers()

    // suspend modifier tells the compiler that this needs to be called from a coroutine or another suspending function.
    suspend fun insertUser(user : User) {
        userDao.insertUser(user)
    }
}