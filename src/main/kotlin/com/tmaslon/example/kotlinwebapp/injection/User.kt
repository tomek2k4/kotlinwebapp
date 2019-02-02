package com.tmaslon.example.kotlinwebapp.injection

import javax.inject.Inject

class User @Inject constructor() {
    fun getName() = "Tomek"
}
