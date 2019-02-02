package com.tmaslon.example.kotlinwebapp

import com.tmaslon.example.kotlinwebapp.injection.User
import spark.Spark.get
import javax.inject.Inject

class ServiceController {

    @Inject
    lateinit var user: User

    init {
        ServiceRunner.serviceComponent.inject(this)

        get("/hello", { req, res -> user.getName() })
    }
}
