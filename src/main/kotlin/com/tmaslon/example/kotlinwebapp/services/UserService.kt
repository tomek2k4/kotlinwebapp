package com.tmaslon.example.kotlinwebapp.services

import com.tmaslon.example.kotlinwebapp.models.User

interface UserService {

    fun isValidUser(userId: Long): Boolean
    fun getUserBalance(userId: Long): Double
    fun getAllUsers(): List<User>
}
