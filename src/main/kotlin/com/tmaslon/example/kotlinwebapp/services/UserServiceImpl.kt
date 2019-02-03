package com.tmaslon.example.kotlinwebapp.services

import com.tmaslon.example.kotlinwebapp.models.User
import com.tmaslon.example.kotlinwebapp.repositories.UserRepository
import javax.inject.Inject

class UserServiceImpl
@Inject constructor(private val userRepository: UserRepository) : UserService {

    override fun isValidUser(userId: Long): Boolean =
        userRepository.getAllUsers().any { it.id == userId }

    override fun getUserBalance(userId: Long): Double =
        userRepository.getAllUsers().first { it.id == userId }.balance

    override fun getAllUsers(): List<User> = userRepository.getAllUsers()
}
