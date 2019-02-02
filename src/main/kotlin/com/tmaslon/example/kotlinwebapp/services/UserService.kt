package com.tmaslon.example.kotlinwebapp.services

import com.tmaslon.example.kotlinwebapp.models.User

interface UserService {
    fun getAllUsers(): List<User>
}
