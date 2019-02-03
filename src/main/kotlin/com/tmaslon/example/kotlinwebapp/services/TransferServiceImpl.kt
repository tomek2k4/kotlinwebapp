package com.tmaslon.example.kotlinwebapp.services

import com.tmaslon.example.kotlinwebapp.api.Response
import com.tmaslon.example.kotlinwebapp.api.Response.Failure
import com.tmaslon.example.kotlinwebapp.api.TransferRequest
import com.tmaslon.example.kotlinwebapp.api.TransferResponse
import com.tmaslon.example.kotlinwebapp.repositories.TransferTransactionExecutor
import java.util.concurrent.locks.ReadWriteLock
import javax.inject.Inject
import kotlin.concurrent.withLock

class TransferServiceImpl
@Inject constructor(
    private val userService: UserService,
    private val readWriteLock: ReadWriteLock,
    private val transferTransactionExecutor: TransferTransactionExecutor
) : TransferService {

    override fun transfer(transferRequest: TransferRequest): TransferResponse {
        transferRequest.validateTransferInput()?.let { return it }

//        return readWriteLock.readLock().withLock {
        return execute(transferRequest)
//        }
    }

    private fun TransferRequest.validateTransferInput(): TransferResponse? =
        when {
            isAmountValid().not() -> TransferResponse(Failure.name, "Amount should be positive value")
            isRecipientValid().not() -> TransferResponse(Failure.name, "Recipient does not exist")
            this.holder == this.accountTo -> TransferResponse(Failure.name, "Recipient should be different than holder")
            else -> null
        }

    private fun execute(transferRequest: TransferRequest): TransferResponse {
        val balance = userService.getUserBalance(transferRequest.holder)
        if (balance < transferRequest.amount) return TransferResponse(Failure.name, "Insufficient funds on account")
        return try {
            readWriteLock.writeLock().withLock {
                transferTransactionExecutor.execute(transferRequest)
            }
            TransferResponse(Response.Success.name, "Transfer successful")
        } catch (ex: RuntimeException) {
            TransferResponse(Response.Failure.name, ex.message ?: "Transfer failed")
        }
    }

    private fun TransferRequest.isAmountValid(): Boolean = amount > 0
    private fun TransferRequest.isRecipientValid(): Boolean = userService.isValidUser(accountTo)
}

