package com.tmaslon.example.kotlinwebapp.controllers

import com.google.gson.Gson
import com.tmaslon.example.kotlinwebapp.ServiceRunner
import com.tmaslon.example.kotlinwebapp.services.UserService
import spark.Spark.get
import javax.inject.Inject

class UserController {

    @Inject
    lateinit var userService: UserService

    @Inject
    lateinit var gson: Gson

    init {
        ServiceRunner.serviceComponent.plusUserComponent().inject(this)
    }

    fun initRoute() {
        get("/users", { req, res ->
            userService.getAllUsers()
        }, gson::toJson)

    }

}
