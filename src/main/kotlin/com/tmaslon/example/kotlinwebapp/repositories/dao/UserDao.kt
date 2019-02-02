package com.tmaslon.example.kotlinwebapp.repositories.dao

import com.tmaslon.example.kotlinwebapp.database.DatabaseHelper
import com.tmaslon.example.kotlinwebapp.models.User
import kotliquery.Row
import kotliquery.queryOf
import javax.inject.Inject

interface UserDao {
    fun getUsers(): List<User>
}

class UserDaoImpl
@Inject constructor(private val databaseHelper: DatabaseHelper) : UserDao {

    val toUser: (Row) -> User = { row ->
        User(row.long("id"), row.string("name"), row.double("balance"))
    }

    override fun getUsers(): List<User> =
        databaseHelper.session.run(queryOf(getUsersQuery()).map(toUser).asList)

    private fun getUsersQuery() = "select id, name, balance from users"

}
