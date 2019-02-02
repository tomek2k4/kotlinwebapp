package com.tmaslon.example.kotlinwebapp.injection.module

import com.tmaslon.example.kotlinwebapp.repositories.UserRepository
import com.tmaslon.example.kotlinwebapp.repositories.dao.UserDao
import com.tmaslon.example.kotlinwebapp.repositories.dao.UserDaoImpl
import com.tmaslon.example.kotlinwebapp.services.UserService
import com.tmaslon.example.kotlinwebapp.services.UserServiceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class UserModule {

    @Binds
    abstract fun bindUserDao(userDao: UserDaoImpl): UserDao

    @Binds
    abstract fun bindUserRepository(userRepository: UserRepository.Database): UserRepository

    @Binds
    abstract fun bindUserService(userService: UserServiceImpl): UserService
}
