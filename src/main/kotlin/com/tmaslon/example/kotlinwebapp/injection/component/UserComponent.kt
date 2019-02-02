package com.tmaslon.example.kotlinwebapp.injection.component

import com.tmaslon.example.kotlinwebapp.controllers.UserController
import com.tmaslon.example.kotlinwebapp.injection.module.UserModule
import dagger.Subcomponent

@Subcomponent(modules = [UserModule::class])
interface UserComponent {
    fun inject(userController: UserController)
}
