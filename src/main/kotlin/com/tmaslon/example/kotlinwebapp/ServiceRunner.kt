package com.tmaslon.example.kotlinwebapp

import spark.Spark

class ServiceRunner {

    fun run(){
        Spark.get("/hello", { req, res -> "Hello Spark!!!"})
    }
}