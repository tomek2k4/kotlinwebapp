package com.tmaslon.example.kotlinwebapp.services

import com.tmaslon.example.kotlinwebapp.repositories.UserRepository
import javax.inject.Inject

class UserServiceImpl
@Inject constructor(private val userRepository: UserRepository) : UserService {

    override fun isValidUser(userId: Long): Boolean =
        userRepository.getAllUsers().any { it.id == userId }
}
