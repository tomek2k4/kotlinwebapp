package com.tmaslon.example.kotlinwebapp.injection.component

import com.tmaslon.example.kotlinwebapp.controllers.TransferController
import com.tmaslon.example.kotlinwebapp.injection.module.TransferModule
import dagger.Subcomponent

@Subcomponent(modules = [TransferModule::class])
interface TransferComponent {
    fun inject(transferController: TransferController)
}
