package com.tmaslon.example.kotlinwebapp.database

import kotliquery.Session
import kotliquery.queryOf
import kotliquery.sessionOf
import kotliquery.using
import javax.inject.Inject
import javax.sql.DataSource

class DatabaseHelperImpl
@Inject constructor(dataSource: DataSource) : DatabaseHelper {

    override val session: Session = sessionOf(dataSource)

    override fun init() {
        using(session) { session ->
            session.run(queryOf(createUsersTable()).asExecute)
            listOf("Jan Kowalski", "Maria Nowak", "Jacek Placek")
                .forEachIndexed { index, name ->
                    session.run(queryOf(insertUserQuery(), name, index * 100).asUpdate)
                }
        }
    }

    private fun createUsersTable() =
        """
          create table users (
            id serial not null primary key,
            name varchar(255),
            balance double default 100,
          )
        """

    private fun insertUserQuery() = "insert into users (name,  balance) values (?, ?)"
}
