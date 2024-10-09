package com.example.locusmobile.fake

import com.example.locusmobile.data.model.User
import com.example.locusmobile.data.repository.UsersRepository

class FakeUsersRepository: UsersRepository {
    override suspend fun getUser(id: String): Result<User> {
        return when(id) {
            "1" -> Result.success(User("1","Adam","Shkolnik"))
            else -> Result.failure(Throwable("User Not Found"))
        }
    }
}