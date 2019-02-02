package com.tmaslon.example.kotlinwebapp.injection.module

import com.tmaslon.example.kotlinwebapp.database.DatabaseHelper
import com.tmaslon.example.kotlinwebapp.database.DatabaseHelperImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotliquery.HikariCP
import javax.sql.DataSource

@Module
abstract class ServiceModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideDataSource(): DataSource = HikariCP.default("jdbc:h2:mem:bank", "user", "pass")
    }

    @Binds
    abstract fun bindsDatabaseHelper(databaseHelper: DatabaseHelperImpl): DatabaseHelper
}
