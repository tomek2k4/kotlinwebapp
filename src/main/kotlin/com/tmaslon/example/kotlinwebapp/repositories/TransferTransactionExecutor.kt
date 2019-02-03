package com.tmaslon.example.kotlinwebapp.repositories

import com.tmaslon.example.kotlinwebapp.api.TransferRequest
import com.tmaslon.example.kotlinwebapp.database.DatabaseHelper
import kotliquery.Row
import kotliquery.queryOf
import javax.inject.Inject

interface TransferTransactionExecutor {
    fun execute(transferRequest: TransferRequest): Boolean
}

class TransferTransactionExecutorImpl
@Inject constructor(private val databaseHelper: DatabaseHelper) : TransferTransactionExecutor {

    private val toUserBalance: (Row) -> Double = { row -> row.double("balance") }

    override fun execute(transferRequest: TransferRequest): Boolean =
        databaseHelper.session.transaction { tx ->
            val holder = transferRequest.holder
            val recipient = transferRequest.accountTo
            val holderBalance = tx.run(queryOf(getUserBalanceQuery(), holder).map(toUserBalance).asSingle)
            val recBalance = tx.run(queryOf(getUserBalanceQuery(), recipient).map(toUserBalance).asSingle)
            holderBalance ?: throw RuntimeException("Holder balance does not exists")
            recBalance ?: throw RuntimeException("Recipient balance does not exists")
            val newHolderBalance = holderBalance - transferRequest.amount
            val newRecipientBalance = recBalance + transferRequest.amount
            val hCount = tx.run(queryOf(setNewBalanceQuery(), newHolderBalance, holder).asUpdate)
            val recCount = tx.run(queryOf(setNewBalanceQuery(), newRecipientBalance, recipient).asUpdate)

            if (hCount != 1 || recCount != 1) throw RuntimeException("Transfer failed")
            else true
        }

    private fun getUserBalanceQuery() = "select balance from users where id = ?"
    private fun setNewBalanceQuery() = "update users set balance = ? where id = ?"
}
