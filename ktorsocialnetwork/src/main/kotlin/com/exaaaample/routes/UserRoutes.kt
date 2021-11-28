package com.exaaaample.routes

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.exaaaample.data.repository.user.UserRepository
import com.exaaaample.data.requests.CreateAccountRequest
import com.exaaaample.data.requests.LoginRequest
import com.exaaaample.data.responses.AuthResponse
import com.exaaaample.data.responses.BasicApiResponse
import com.exaaaample.service.UserService
import com.exaaaample.util.ApiResponseMessages.FIELDS_BLANK
import com.exaaaample.util.ApiResponseMessages.INVALID_CREDENTIALS
import com.exaaaample.util.ApiResponseMessages.USER_ALREADY_EXISTS
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import java.util.*

fun Route.createUser(userService: UserService) {
    route("/api/user/create") {
        post {
            val request = call.receiveOrNull<CreateAccountRequest>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }
            if (userService.doesUserWithEmailExist(request.email)) {
                call.respond(
                    BasicApiResponse(
                        successful = false,
                        message = USER_ALREADY_EXISTS
                    )
                )
                return@post
            }
            when (userService.validateCreateAccountRequest(request)) {
                is UserService.ValidationEvent.ErrorFieldEmpty -> {
                    call.respond(
                        BasicApiResponse(
                            successful = false,
                            message = FIELDS_BLANK
                        )
                    )
                    return@post
                }
                is UserService.ValidationEvent.Success -> {
                    userService.createUser(request)
                    call.respond(
                        BasicApiResponse(successful = true)
                    )
                }
            }
        }
    }
}

fun Route.loginUser(
    userService: UserService,
    jwtIssuer: String,
    jwtAudience: String,
    jwtSecret: String,
) {

    post("/api/user/login") {
        val request = call.receiveOrNull<LoginRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        if (request.email.isBlank() || request.password.isBlank()) {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        val isCorrectPassword = userService.doesPasswordForUserMatch(request)
        if (isCorrectPassword) {

            // ------------------------------------
            // Create JWT
            val expiresIn = 1000L * 60L * 60L * 24L * 365L // 1 year
            val token = JWT.create()
                .withClaim("email", request.email)  // Extra data ?
                .withIssuer(jwtIssuer)
                .withExpiresAt(Date(System.currentTimeMillis() + expiresIn))
                .withAudience(jwtAudience)
                .sign(Algorithm.HMAC256(jwtSecret))
            call.respond(
                HttpStatusCode.OK,
                AuthResponse(token = token)
            )
        } else {
            call.respond(
                HttpStatusCode.OK,
                BasicApiResponse(
                    successful = false,
                    message = INVALID_CREDENTIALS
                )
            )
        }
    }
}
