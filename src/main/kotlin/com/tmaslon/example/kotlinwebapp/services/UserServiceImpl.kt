package com.tmaslon.example.kotlinwebapp.services

import com.tmaslon.example.kotlinwebapp.models.User
import com.tmaslon.example.kotlinwebapp.repositories.UserRepository
import java.util.concurrent.locks.ReadWriteLock
import javax.inject.Inject
import kotlin.concurrent.withLock

class UserServiceImpl
@Inject constructor(
    private val userRepository: UserRepository,
    private val readWriteLock: ReadWriteLock
) : UserService {

    override fun isValidUser(userId: Long): Boolean =
        userRepository.getAllUsers().any { it.id == userId }

    override fun getUserBalance(userId: Long): Double =
        readWriteLock.readLock().withLock {
            userRepository.getAllUsers().first { it.id == userId }.balance
        }

    override fun getAllUsers(): List<User> =
        readWriteLock.readLock().withLock {
            userRepository.getAllUsers()
        }

    override fun getUser(userId: Long): User =
        readWriteLock.readLock().withLock {
            userRepository.getAllUsers().first { it.id == userId }
        }
}
