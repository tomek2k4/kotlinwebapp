package com.tmaslon.example.kotlinwebapp.controllers

import com.tmaslon.example.kotlinwebapp.ServiceRunner
import com.tmaslon.example.kotlinwebapp.database.DatabaseHelper
import com.tmaslon.example.kotlinwebapp.injection.User
import spark.Spark.get
import javax.inject.Inject

class ServiceController {

    @Inject
    lateinit var databaseHelper: DatabaseHelper

    init {
        ServiceRunner.serviceComponent.inject(this)
        databaseHelper.init()
        get("/hello", { req, res -> "Hello Spark" })
    }
}
