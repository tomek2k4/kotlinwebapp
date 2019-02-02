package com.tmaslon.example.kotlinwebapp.controllers

import com.tmaslon.example.kotlinwebapp.ServiceRunner
import com.tmaslon.example.kotlinwebapp.database.DatabaseHelper
import spark.Spark.get
import javax.inject.Inject

class ServiceController {

    @Inject
    lateinit var databaseHelper: DatabaseHelper

    init {
        ServiceRunner.serviceComponent.inject(this)
        databaseHelper.init()

        initRoutes()
    }

    private fun initRoutes() {
        UserController().initRoute()
        get("/hello", { req, res -> "Hello Spark" })
    }
}
