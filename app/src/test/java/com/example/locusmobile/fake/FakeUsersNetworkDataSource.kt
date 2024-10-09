package com.example.locusmobile.fake

import com.example.locusmobile.data.model.User
import com.example.locusmobile.data.source.UsersNetworkDataSource
import kotlinx.coroutines.delay

class FakeUsersNetworkDataSource: UsersNetworkDataSource {
    override suspend fun getUser(id: String): User? {
        delay(1000)
        return FakeUsersData.users[id]
    }
}