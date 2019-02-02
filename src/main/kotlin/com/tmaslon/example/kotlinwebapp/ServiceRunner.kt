package com.tmaslon.example.kotlinwebapp

import com.tmaslon.example.kotlinwebapp.controllers.ServiceController
import com.tmaslon.example.kotlinwebapp.injection.component.DaggerServiceComponent
import com.tmaslon.example.kotlinwebapp.injection.component.ServiceComponent

class ServiceRunner {

    companion object {
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
