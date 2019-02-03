package com.tmaslon.example.kotlinwebapp.services

import com.tmaslon.example.kotlinwebapp.api.TransferRequest

interface TransferService {

    fun transfer(transferRequest: TransferRequest)
}
