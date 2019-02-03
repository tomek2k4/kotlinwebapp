package com.tmaslon.example.kotlinwebapp.controllers

import com.google.gson.Gson
import com.tmaslon.example.kotlinwebapp.ServiceRunner
import com.tmaslon.example.kotlinwebapp.services.UserService
import spark.Spark.*
import javax.inject.Inject

class TransferController {

    @Inject
    lateinit var userService: UserService

    @Inject
    lateinit var gson: Gson

    init {
        ServiceRunner.serviceComponent.plusUserComponent().inject(this)
    }

    fun initRoute() {
        path("/:user") {
            before("") { req, _ ->
                val userParam = req.params(":user")
                ServiceRunner.logger.info("Received action for user id: $userParam")
                userParam.toLongOrNull()?.let {
                    if (userService.isValidUser(it).not()) halt(401, "Unauthorized user")
                } ?: halt(400, "User id should be a number")
            }
        }
    }
}