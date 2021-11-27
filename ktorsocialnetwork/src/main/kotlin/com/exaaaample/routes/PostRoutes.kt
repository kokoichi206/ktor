package com.exaaaample.routes

import com.exaaaample.data.models.Post
import com.exaaaample.data.repository.post.PostRepository
import com.exaaaample.data.requests.CreatePostRequest
import com.exaaaample.data.requests.FollowUpdateRequest
import com.exaaaample.data.responses.BasicApiResponse
import com.exaaaample.service.PostService
import com.exaaaample.util.ApiResponseMessages
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.createPostRoute(postService: PostService) {
    post("/api/post/create") {
        val request = call.receiveOrNull<CreatePostRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        val didUserExist = postService.createPostIfUserExists(request)
        if (!didUserExist) {
            call.respond(
                HttpStatusCode.OK,
                BasicApiResponse(
                    successful = false,
                    message = ApiResponseMessages.USER_NOT_FOUND
                )
            )
        } else {
            call.respond(
                HttpStatusCode.OK,
                BasicApiResponse(
                    successful = true,
                )
            )
        }
    }
}
