package com.example.locusmobile.data.repository

import com.example.locusmobile.data.model.User

interface UsersRepository {
    suspend fun getUser(id: String): Result<User>
}