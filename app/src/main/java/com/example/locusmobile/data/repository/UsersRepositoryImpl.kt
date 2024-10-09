package com.example.locusmobile.data.repository

import com.example.locusmobile.data.model.User
import com.example.locusmobile.data.source.UsersLocalDataSource
import com.example.locusmobile.data.source.UsersNetworkDataSource

class UsersRepositoryImpl(
    private val usersNetworkDataSource: UsersNetworkDataSource,
    private val usersLocalDataSource: UsersLocalDataSource
): UsersRepository {
    override suspend fun getUser(id: String): Result<User> {
        val user = usersNetworkDataSource.getUser(id)

        return when (user != null) {
            true -> Result.success(User(user.id, user.firstName, user.lastName))
            else -> Result.failure(Throwable("No User Found"))
        }
    }
}