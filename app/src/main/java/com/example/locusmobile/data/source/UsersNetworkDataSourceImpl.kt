package com.example.locusmobile.data.source

import com.example.locusmobile.data.model.User
import kotlinx.coroutines.delay

class UsersNetworkDataSourceImpl: UsersNetworkDataSource {
    override suspend fun getUser(id: String): User? {
        delay(1000)
        return FakeUsersData.users[id]
    }
}