package com.example.locusmobile.data.source

import com.example.locusmobile.data.model.User
import com.example.locusmobile.data.network.UserApiService

class UsersNetworkDataSource(private val userApiService: UserApiService) {
    suspend fun getUser(id: String): User {
        return userApiService.getUser(id)
    }
}