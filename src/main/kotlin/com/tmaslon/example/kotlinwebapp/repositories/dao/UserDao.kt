package com.tmaslon.example.kotlinwebapp.repositories.dao

import com.tmaslon.example.kotlinwebapp.database.DatabaseHelper
import com.tmaslon.example.kotlinwebapp.models.User
import com.tmaslon.example.kotlinwebapp.repositories.dao.UserDao.Companion.toUser
import kotliquery.Row
import kotliquery.queryOf
import javax.inject.Inject

interface UserDao {

    companion object {
        val toUser: (Row) -> User = { row ->
            User(row.long("id"), row.string("name"), row.double("balance"))
        }
    }

    fun getUsers(): List<User>
}

class UserDaoImpl
@Inject constructor(private val databaseHelper: DatabaseHelper) : UserDao {

    override fun getUsers(): List<User> =
        databaseHelper.session.run(queryOf(getUsersQuery()).map(toUser).asList)

    private fun getUsersQuery() = "select id, name, balance from users"
}
