package com.tmaslon.example.kotlinwebapp.controllers

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.tmaslon.example.kotlinwebapp.ServiceRunner
import com.tmaslon.example.kotlinwebapp.api.TransferRequest
import com.tmaslon.example.kotlinwebapp.services.TransferService
import com.tmaslon.example.kotlinwebapp.services.UserService
import spark.Request
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
            before("") { req, _ -> verifyUser(req) }
            before("/*") { req, _ -> verifyUser(req) }
            get("") { req, res ->
                val user = req.params(":user").toLong()
                mapper.writeValueAsString(userService.getUser(user))
            }
            path("/transfer") {
                before("") { req, _ ->
                    if (req.body().isEmpty()) badRequest("Transfer needs to have a body")
                }
                post("") { req, res ->
                    val holder = req.params(":user").toLong()
                    val transfer = mapper.readTree(req.body())
                    if (isValidTransferNode(transfer).not()) badRequest("Transfer needs to have amount and account_to filled")
                    mapper.writeValueAsString(handleTransfer(holder, transfer))
                }
            }
        }
    }

    private fun isValidTransferNode(transferNode: JsonNode) =
        transferNode.has("amount") && transferNode.has("account_to")

    private fun handleTransfer(holderId: Long, transferNode: JsonNode) =
        transferService.transfer(
            TransferRequest(
                holderId,
                transferNode.get("amount").asDouble(),
                transferNode.get("account_to").asLong()
            )
        )

    private fun verifyUser(req: Request) {
        val userParam = req.params(":user")
        userParam.toLongOrNull()?.let {
            if (userService.isValidUser(it).not()) unauthorized("Unauthorized user")
        } ?: badRequest("User id should be a number")
    }

    private fun badRequest(reason: String) = halt(400, reason)
    private fun unauthorized(reason: String) = halt(401, reason)
    private fun serverError(reason: String) = halt(500, reason)
}
