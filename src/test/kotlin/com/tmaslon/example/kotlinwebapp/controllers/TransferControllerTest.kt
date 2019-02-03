package com.tmaslon.example.kotlinwebapp.controllers

import com.github.kittinunf.fuel.Fuel
import com.tmaslon.example.kotlinwebapp.main
import org.junit.AfterClass
import org.junit.Assert
import org.junit.BeforeClass
import org.junit.Test
import spark.Spark

class TransferControllerTest {

    companion object {

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

    @Test
    fun `receive bad request response for literal user id`() {
        val get = Fuel.get("http://localhost:4567/jankowalski").responseString()
        Assert.assertEquals(400, get.second.httpStatusCode)
    }

    @Test
    fun `receive unauthorized request response for not existing user`() {
        val get = Fuel.get("http://localhost:4567/123").responseString()
        Assert.assertEquals(401, get.second.httpStatusCode)
    }
}
