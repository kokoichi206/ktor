package com.exaaaample.routes

import com.exaaaample.repository.user.UserRepository
import com.exaaaample.data.models.User
import com.exaaaample.data.requests.CreateAccountRequest
import com.exaaaample.data.responses.BasicApiResponse
import com.exaaaample.util.ApiResponseMessages.FIELDS_BLANK
import com.exaaaample.util.ApiResponseMessages.USER_ALREADY_EXISTS
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.createUserRoute(userRepository: UserRepository) {
    route("/api/user/create") {
        post {
            val request = call.receiveOrNull<CreateAccountRequest>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }
            val userExists = userRepository.getUserByEmail(request.email) != null
            if (userExists) {
                call.respond(
                    BasicApiResponse(
                        successful = false,
                        message = USER_ALREADY_EXISTS
                    )
                )
                return@post
            }
            if (request.email.isBlank() || request.password.isBlank() || request.username.isBlank()) {
                call.respond(
                    BasicApiResponse(
                        successful = false,
                        message = FIELDS_BLANK
                    )
                )
                return@post
            }
            userRepository.createUser(
                User(
                    email = request.email,
                    username = request.username,
                    password = request.password,
                    profileImageUrl = "",
                    bio = "",
                    gitHubUrl = null,
                    instagramUrl = null,
                    linkedInUrl = null
                )
            )
            call.respond(
                BasicApiResponse(successful = true)
            )
        }
    }
}
