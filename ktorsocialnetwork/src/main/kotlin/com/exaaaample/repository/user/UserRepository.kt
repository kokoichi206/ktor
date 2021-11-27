package com.exaaaample.repository.user

import com.exaaaample.data.models.User

interface UserRepository {

    suspend fun createUser(user: User)

    suspend fun getUserById(id: String): User?

    suspend fun getUserByEmail(email: String): User?
}