package com.tmaslon.example.kotlinwebapp.database

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.tmaslon.example.kotlinwebapp.models.User
import com.tmaslon.example.kotlinwebapp.repositories.dao.UserDao
import kotliquery.queryOf
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.sql.DriverManager
import javax.sql.DataSource


class DatabaseHelperImplTest {

    val dataSourceMock: DataSource = mock()
    lateinit var tested: DatabaseHelper

    @Before
    fun setUp() {
        val conn = DriverManager.getConnection("jdbc:h2:mem:hello", "user", "pass")
        whenever(dataSourceMock.connection).thenReturn(conn)
        tested = DatabaseHelperImpl(dataSourceMock)
        tested.init()
    }

    @Test
    fun `checks initial values`() {
        val db = tested.session
        val members: List<User> = db.run(queryOf("select id, name, balance from users").map(UserDao.toUser).asList)
        assertEquals(3, members.size)
    }
}
