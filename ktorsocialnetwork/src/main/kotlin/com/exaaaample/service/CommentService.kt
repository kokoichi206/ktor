package com.exaaaample.service

import com.exaaaample.data.models.Comment
import com.exaaaample.data.repository.comment.CommentRepository
import com.exaaaample.data.requests.CreateCommentRequest
import com.exaaaample.util.Constants

class CommentService(
    private val repository: CommentRepository
) {

    suspend fun createComment(createCommentRequest: CreateCommentRequest, userId: String): ValidationEvents {
        createCommentRequest.apply {
            if (comment.isBlank() || postId.isBlank()) {
                return ValidationEvents.ErrorFieldEmpty
            }
            if (comment.length > Constants.MAX_COMMENT_SIZE) {
                return ValidationEvents.ErrorCommentTooLong
            }
        }
        repository.createComment(
            Comment(
                comment = createCommentRequest.comment,
                userId = userId,
                postId = createCommentRequest.postId,
                timestamp = System.currentTimeMillis()
            )
        )
        return ValidationEvents.Success
    }

    suspend fun deleteComment(commentId: String): Boolean {
        return repository.deleteComment(commentId)
    }

    suspend fun getCommentsForPost(postId: String): List<Comment> {
        return repository.getCommentsForPost(postId)
    }

    suspend fun getCommentById(commentId: String): Comment? {
        return repository.getComment(commentId)
    }

    sealed class ValidationEvents {
        object ErrorFieldEmpty : ValidationEvents()
        object ErrorCommentTooLong : ValidationEvents()
        object Success : ValidationEvents()
    }
}