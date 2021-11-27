package com.exaaaample.plugins

import com.exaaaample.data.repository.follow.FollowRepository
import com.exaaaample.data.repository.user.UserRepository
import com.exaaaample.routes.createUserRoute
import com.exaaaample.routes.followUser
import com.exaaaample.routes.loginUser
import com.exaaaample.routes.unfollowUser
import io.ktor.routing.*
import io.ktor.http.content.*
import io.ktor.application.*
import io.ktor.response.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val userRepository: UserRepository by inject()
    val followRepository: FollowRepository by inject()
    routing {
        // User routes
        createUserRoute(userRepository)
        loginUser(userRepository)

        // Following routes
        followUser(followRepository)
        unfollowUser(followRepository)

        get("/") {
                call.respondText("Hello World!")
            }
        // Static plugin. Try to access `/static/index.html`
        static("/static") {
            resources("static")
        }
    }
}
