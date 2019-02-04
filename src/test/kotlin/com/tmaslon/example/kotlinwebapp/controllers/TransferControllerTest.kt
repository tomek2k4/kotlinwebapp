package com.tmaslon.example.kotlinwebapp.controllers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.kittinunf.fuel.Fuel
import com.tmaslon.example.kotlinwebapp.api.Response
import com.tmaslon.example.kotlinwebapp.main
import kotlinx.coroutines.runBlocking
import org.junit.AfterClass
import org.junit.Assert
import org.junit.BeforeClass
import org.junit.Test
import spark.Spark

class TransferControllerTest {

    companion object {

        private val BASE_URL = "http://localhost:4567"

        @BeforeClass
        @JvmStatic
        fun beforeClass() {
            main()
        }

        @AfterClass
        @JvmStatic
        fun afterClass() {
            Spark.stop()
        }
    }

    private val mapper = jacksonObjectMapper()

    private fun transferRequest(amount: Double, accountTo: Long) = """
        {
            "amount": "$amount",
            "account_to": "$accountTo"
        }
    """

    @Test
    fun `receive bad request response for literal user id`() {
        val get = Fuel.get("$BASE_URL/jankowalski").responseString()
        Assert.assertEquals(400, get.second.httpStatusCode)
    }

    @Test
    fun `receive unauthorized request response for not existing user`() {
        val get = Fuel.get("$BASE_URL/123").responseString()
        Assert.assertEquals(401, get.second.httpStatusCode)
    }

    @Test
    fun `receive bad request response for empty body in request`() {
        val get = Fuel.get("$BASE_URL/1/transfer").responseString()
        Assert.assertEquals(400, get.second.httpStatusCode)
    }

    @Test
    fun `receive unauthorized request response for transfer from not existing holder`() {
        val get = Fuel.get("$BASE_URL/5/transfer").responseString()
        Assert.assertEquals(401, get.second.httpStatusCode)
    }

    @Test
    fun `make a transfer from one user to another`() {
        assertBalance(1L, 0.0)

        val expectedBalance = 1.0
        makeTransfer(2L, transferRequest(1.0, 1L))

        assertBalance(1L, expectedBalance)
        assertBalance(2L, 99.0)
        makeTransfer(1L, transferRequest(1.0, 2L)) // return back for test cleanup
    }

    @Test
    fun `make a hundred sequential transfers from one user to another`() {
        assertBalance(1L, 0.0)

        val expectedBalance = 100.0
        repeat(100) {
            makeTransfer(2L, transferRequest(1.0, 1L))
        }

        assertBalance(1L, expectedBalance)
        assertBalance(2L, 0.0)
        makeTransfer(1L, transferRequest(expectedBalance, 2L))
    }

    @Test
    fun `make a hundred concurrent transfers from one user to another`() {
        assertBalance(1L, 0.0)

        val expectedBalance = 100.0
        runBlocking {
            repeat(100) {
                makeTransfer(2L, transferRequest(1.0,1L))
            }
        }
        assertBalance(1L, expectedBalance)
        assertBalance(2L, 0.0)
        makeTransfer(1L,transferRequest(expectedBalance,2L))
    }

    @Test
    fun `make a one hundred thousand concurrent transfers from one user to another`() {
        assertBalance(1L, 0.0)

        val expectedBalance = 100.0
        runBlocking {
            repeat(100_000) {
                makeTransfer(2L, transferRequest(0.001,1L))
            }
        }
        assertBalance(1L, expectedBalance)
        assertBalance(2L, 0.0)
        makeTransfer(1L,transferRequest(expectedBalance,2L))
    }


    private fun assertBalance(userId: Long, expected: Double) {
        val user = Fuel.get("$BASE_URL/$userId").responseString().third.get()
        val balance = mapper.readTree(user).get("balance").asDouble()
        Assert.assertEquals(expected, balance, 0.00001)
    }

    private fun makeTransfer(from: Long, transfer: String) {
        val post = Fuel.post("$BASE_URL/$from/transfer").body(transfer).responseString().third.get()
        Assert.assertEquals(Response.Success.name, mapper.readTree(post).get("response").asText())
    }
}
