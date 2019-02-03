package com.tmaslon.example.kotlinwebapp

import com.tmaslon.example.kotlinwebapp.controllers.ServiceController
import com.tmaslon.example.kotlinwebapp.injection.component.DaggerServiceComponent
import com.tmaslon.example.kotlinwebapp.injection.component.ServiceComponent
import org.slf4j.LoggerFactory

class ServiceRunner {

    companion object {
        val logger = LoggerFactory.getLogger(ServiceRunner::class.java)

        //platformStatic allow access it from java code
        lateinit var serviceComponent: ServiceComponent
    }

    init {
        serviceComponent = DaggerServiceComponent.create()
    }

    fun run() {
        initControllers()
    }

    private fun initControllers() {
        ServiceController()
    }
}
