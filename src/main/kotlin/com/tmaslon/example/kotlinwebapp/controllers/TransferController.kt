package com.tmaslon.example.kotlinwebapp.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.tmaslon.example.kotlinwebapp.ServiceRunner
import com.tmaslon.example.kotlinwebapp.services.TransferService
import com.tmaslon.example.kotlinwebapp.services.UserService
import spark.Spark.*
import javax.inject.Inject

class TransferController {

    @Inject
    lateinit var userService: UserService

    @Inject
    lateinit var transferService: TransferService

    @Inject
    lateinit var mapper: ObjectMapper

    init {
        ServiceRunner.serviceComponent.plusTransferComponent().inject(this)
    }

    fun initRoute() {
        path("/:user") {
            before("") { req, _ ->
                val userParam = req.params(":user")
                ServiceRunner.logger.info("Received action for user id: $userParam")
                userParam.toLongOrNull()?.let {
                    if (userService.isValidUser(it).not()) unauthorized("Unauthorized user")
                } ?: badRequest("User id should be a number")
            }

            path("/transfer") {
                before("") { req, _ ->
                    if (req.body().isEmpty())
                        badRequest("Transfer needs to have a body")
                }
                post("") { req, res ->
                    val transfer = mapper.readTree(req.body())
                    if (transfer.has("amount").not() || transfer.has("account_to").not()) {
                        badRequest("Transfer needs to have amount and account_to filled")
                    } else {

                    }
                }
            }
        }
    }

    private fun badRequest(reason: String) = halt(400, reason)
    private fun unauthorized(reason: String) = halt(401, reason)
    private fun serverError(reason: String) = halt(500, reason)
}
