package com.tmaslon.example.kotlinwebapp.repositories.dao

import com.tmaslon.example.kotlinwebapp.database.DatabaseHelperImpl
import kotliquery.HikariCP
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UserDaoImplTest {
    val databaseHelper = DatabaseHelperImpl(HikariCP.default("jdbc:h2:mem:bank", "user", "pass"))
    val tested = UserDaoImpl(databaseHelper)

    @Before
    fun setup() {
        databaseHelper.init()
    }

    @Test
    fun `get all users`() {
        val result = tested.getUsers()

        assertEquals(3, result.size)
    }

}
