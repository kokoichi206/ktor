package com.exaaaample.plugins

import com.exaaaample.data.repository.follow.FollowRepository
import com.exaaaample.data.repository.post.PostRepository
import com.exaaaample.data.repository.user.UserRepository
import com.exaaaample.routes.*
import com.exaaaample.service.FollowService
import com.exaaaample.service.PostService
import com.exaaaample.service.UserService
import io.ktor.routing.*
import io.ktor.http.content.*
import io.ktor.application.*
import io.ktor.response.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val userService: UserService by inject()
    val followService: FollowService by inject()
    val postService: PostService by inject()

    val jwtIssuer = environment.config.property("jwt.domain").getString()
    val jwtAudience = environment.config.property("jwt.audience").getString()
    val jwtSecret = environment.config.property("jwt.secret").getString()
    routing {
        // User routes
        createUserRoute(userService)
        loginUser(
            userService = userService,
            jwtIssuer = jwtIssuer,
            jwtAudience = jwtAudience,
            jwtSecret = jwtSecret
        )

        // Following routes
        followUser(followService)
        unfollowUser(followService)

        // Post routes
        createPostRoute(postService, userService)

        get("/") {
                call.respondText("Hello World!")
            }
        // Static plugin. Try to access `/static/index.html`
        static("/static") {
            resources("static")
        }
    }
}
