package com.example.locusmobile

import com.example.locusmobile.data.model.User
import com.example.locusmobile.data.repository.UsersRepositoryImpl
import com.example.locusmobile.fake.FakeUsersLocalDataSource
import com.example.locusmobile.fake.FakeUsersNetworkDataSource
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Test

class UsersRepositoryImplTest {
    private val repository = UsersRepositoryImpl(FakeUsersNetworkDataSource(),FakeUsersLocalDataSource())

    @Test
    fun usersRepositoryImplTest_getUserCalledAndNoneFound_returnFailure() = runTest {
        val result = repository.getUser("null")
        assert(result.isFailure)
    }

    @Test
    fun networkUsersRepositoryImplTest_getUserCalledAndUserFound_returnSuccess() = runTest {
        val result = repository.getUser("1")
        assert(result.isSuccess)
        assertEquals(result.getOrNull(),User("1","Adam","Shkolnik"))
    }
}