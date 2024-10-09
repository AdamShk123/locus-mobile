package com.example.locusmobile.data.network

import com.example.locusmobile.data.model.User

class NetworkUserApiService: UserApiService {
    override suspend fun getUser(id: String): User {
        TODO("Not yet implemented")
    }
}