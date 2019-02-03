package com.tmaslon.example.kotlinwebapp.injection.component

import com.tmaslon.example.kotlinwebapp.controllers.ServiceController
import com.tmaslon.example.kotlinwebapp.injection.module.ServiceModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ServiceModule::class])
interface ServiceComponent {
    fun plusTransferComponent(): TransferComponent

    fun inject(serviceController: ServiceController)
}
