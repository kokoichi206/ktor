package com.exaaaample.routes

import com.exaaaample.data.repository.follow.FollowRepository
import com.exaaaample.data.requests.FollowUpdateRequest
import com.exaaaample.data.responses.BasicApiResponse
import com.exaaaample.util.ApiResponseMessages.USER_NOT_FOUND
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.followUser(followRepository: FollowRepository) {
    post("/api/following/follow") {
        val request = call.receiveOrNull<FollowUpdateRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        val didUserExist = followRepository.followUserIfExists(
            request.followingUserId,
            request.followedUserId
        )
        if (didUserExist) {
            call.respond(
                HttpStatusCode.OK,
                BasicApiResponse(
                    successful = true
                )
            )
        } else {
            call.respond(
                HttpStatusCode.OK,
                BasicApiResponse(
                    successful = false,
                    message = USER_NOT_FOUND
                )
            )
        }
    }
}

fun Route.unfollowUser(followRepository: FollowRepository) {
    delete("/api/following/unfollow") {
        val request = call.receiveOrNull<FollowUpdateRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@delete
        }
        val didUserExist = followRepository.unfollowUserIfExists(
            request.followingUserId,
            request.followedUserId
        )

        if (didUserExist) {
            call.respond(
                HttpStatusCode.OK,
                BasicApiResponse(
                    successful = true
                )
            )
        } else {
            call.respond(
                HttpStatusCode.OK,
                BasicApiResponse(
                    successful = false,
                    message = USER_NOT_FOUND
                )
            )
        }
    }
}
