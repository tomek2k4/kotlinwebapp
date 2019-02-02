package com.tmaslon.example.kotlinwebapp.injection.component

import com.tmaslon.example.kotlinwebapp.ServiceController
import com.tmaslon.example.kotlinwebapp.injection.module.ServiceModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ServiceModule::class])
interface ServiceComponent {
    fun inject(serviceController: ServiceController)
}
