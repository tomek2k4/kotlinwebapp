package com.tmaslon.example.kotlinwebapp.injection.module

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.tmaslon.example.kotlinwebapp.database.DatabaseHelper
import com.tmaslon.example.kotlinwebapp.database.DatabaseHelperImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotliquery.HikariCP
import java.util.concurrent.locks.ReadWriteLock
import java.util.concurrent.locks.ReentrantReadWriteLock
import javax.inject.Singleton
import javax.sql.DataSource

@Module
abstract class ServiceModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideDataSource(): DataSource = HikariCP.default("jdbc:h2:mem:bank", "user", "pass")

        @JvmStatic
        @Provides
        fun provideObjectMapper(): ObjectMapper = jacksonObjectMapper()

        @Singleton
        @JvmStatic
        @Provides
        fun provideReadWriteLock(): ReadWriteLock = ReentrantReadWriteLock()
    }

    @Binds
    abstract fun bindsDatabaseHelper(databaseHelper: DatabaseHelperImpl): DatabaseHelper
}
