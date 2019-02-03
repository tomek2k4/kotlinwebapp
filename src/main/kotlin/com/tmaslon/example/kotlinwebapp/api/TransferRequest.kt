package com.tmaslon.example.kotlinwebapp.api

data class TransferRequest(val holder: Long, val amount: Double, val accountTo: Long)
