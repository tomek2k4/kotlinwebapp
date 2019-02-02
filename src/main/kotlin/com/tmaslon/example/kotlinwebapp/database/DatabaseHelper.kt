package com.tmaslon.example.kotlinwebapp.database

import kotliquery.Session

interface DatabaseHelper {
    val session: Session
    fun init()
}
