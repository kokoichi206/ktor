package com.exaaaample

import com.exaaaample.di.mainModule
import com.exaaaample.plugins.*
import io.ktor.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.ktor.ext.Koin

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureSockets()
        configureSerialization()
        configureMonitoring()
        configureHTTP()
//        configureSecurity()
        install(Koin) {
            modules(mainModule)
        }
    }.start(wait = true)
}
