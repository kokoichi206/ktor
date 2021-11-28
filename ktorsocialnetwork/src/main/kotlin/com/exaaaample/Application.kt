package com.exaaaample

import com.exaaaample.di.mainModule
import com.exaaaample.plugins.*
import io.ktor.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.ktor.ext.Koin

//fun main() {
//    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
//        install(Koin) {
//            modules(mainModule)
//        }
//        configureRouting()
////        configureSockets()
//        configureSerialization()
//        configureMonitoring()
//        configureHTTP()
//        configureSecurity()
//    }.start(wait = true)
//}
fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    install(Koin) {
        modules(mainModule)
    }
    configureSecurity()
    configureSockets()
    configureRouting()
    configureHTTP()
    configureMonitoring()
    configureSerialization()
}