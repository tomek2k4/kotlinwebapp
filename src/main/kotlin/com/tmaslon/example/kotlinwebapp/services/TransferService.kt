package com.tmaslon.example.kotlinwebapp.services

import com.tmaslon.example.kotlinwebapp.api.TransferRequest
import com.tmaslon.example.kotlinwebapp.api.TransferResponse

interface TransferService {

    fun transfer(transferRequest: TransferRequest): TransferResponse
}
