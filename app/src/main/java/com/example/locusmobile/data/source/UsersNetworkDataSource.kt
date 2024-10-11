package com.example.locusmobile.data.source

import com.example.locusmobile.data.model.User

interface UsersNetworkDataSource {
    suspend fun getUser(id: String): User?
}