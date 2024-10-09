package com.example.locusmobile.data.repository

import com.example.locusmobile.data.model.User
import com.example.locusmobile.data.network.UserApiService
import com.example.locusmobile.data.source.UsersLocalDataSource
import com.example.locusmobile.data.source.UsersNetworkDataSource
import kotlinx.coroutines.delay

class NetworkUsersRepository(
    private val usersNetworkDataSource: UsersNetworkDataSource,
    private val usersLocalDataSource: UsersLocalDataSource
): UsersRepository {
    override suspend fun getUser(id: String): User {
        delay(100)
        return usersNetworkDataSource.getUser(id)
    }
}