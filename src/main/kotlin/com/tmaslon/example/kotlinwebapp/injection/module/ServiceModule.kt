package com.tmaslon.example.kotlinwebapp.injection.module

import com.tmaslon.example.kotlinwebapp.injection.User
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ServiceModule {

    @Singleton
    @Provides
    fun user() = User()
}
