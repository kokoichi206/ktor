package com.exaaaample.repository.user

import com.exaaaample.data.models.User

class FakeUserRepository : UserRepository {

    val users = mutableListOf<User>()

    override suspend fun createUser(user: User) {
        users.add(user)
    }

    override suspend fun getUserById(id: String): User? {
        return users.find { it.id == id }
    }

    override suspend fun getUserByEmail(email: String): User? {
        return users.find { it.email == email }
    }
}