package com.example.locusmobile.data.network

import com.example.locusmobile.data.model.User

interface UserApiService {
    suspend fun getUser(id: String): User
}