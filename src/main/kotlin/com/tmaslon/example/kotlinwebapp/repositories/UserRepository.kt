package com.tmaslon.example.kotlinwebapp.repositories

import com.tmaslon.example.kotlinwebapp.models.User
import com.tmaslon.example.kotlinwebapp.repositories.dao.UserDao
import javax.inject.Inject

interface UserRepository {
    fun getAllUsers(): List<User>

    class Database
    @Inject constructor(private val usersDao: UserDao) : UserRepository {

        override fun getAllUsers(): List<User> =
            usersDao.getUsers()
    }

}
