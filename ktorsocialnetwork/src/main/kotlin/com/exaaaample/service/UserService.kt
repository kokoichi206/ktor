package com.exaaaample.service

import com.exaaaample.data.models.User
import com.exaaaample.data.repository.user.UserRepository
import com.exaaaample.data.requests.CreateAccountRequest
import com.exaaaample.data.requests.LoginRequest

class UserService(
    private val repository: UserRepository
) {

    suspend fun doesUserWithEmailExist(email: String): Boolean {
        return repository.getUserByEmail(email) != null
    }

    suspend fun createUser(request: CreateAccountRequest) {
        repository.createUser(
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
    }

    fun validateCreateAccountRequest(request: CreateAccountRequest): ValidationEvent {
        if (request.email.isBlank() || request.password.isBlank() || request.username.isBlank()) {
            return ValidationEvent.ErrorFieldEmpty
        }
        return ValidationEvent.Success
    }

    suspend fun doesEmailBelongToUserId(email: String, userId: String): Boolean {
        return repository.doesEmailBelongToUserId(email, userId)
    }

    suspend fun doesPasswordForUserMatch(request: LoginRequest): Boolean {
        return repository.doesPasswordForUserMatch(
            email = request.email,
            enteredPassword = request.password
        )
    }

    sealed class ValidationEvent {
        object ErrorFieldEmpty : ValidationEvent()
        object Success : ValidationEvent()
    }
}