package com.tmaslon.example.kotlinwebapp.services

import com.tmaslon.example.kotlinwebapp.api.TransferRequest
import java.util.concurrent.locks.ReadWriteLock
import javax.inject.Inject

class TransferServiceImpl
@Inject constructor(private val userService: UserService,
                    private val readWriteLock: ReadWriteLock) : TransferService {

    override fun transfer(transferRequest: TransferRequest) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
