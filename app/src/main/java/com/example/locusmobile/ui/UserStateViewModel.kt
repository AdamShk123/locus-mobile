package com.example.locusmobile.ui

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.locusmobile.data.repository.UsersRepository
import com.example.locusmobile.data.model.User
import kotlinx.coroutines.delay

class UserStateViewModel(private val usersRepository: UsersRepository): ViewModel() {
    var isLoggedIn by mutableStateOf(false)
    var isBusy by mutableStateOf(false)
    var user by mutableStateOf(User())

    suspend fun signIn(id: String, password: String) {
        isBusy = true
        user = usersRepository.getUser(id)
        delay(2000)
        isLoggedIn = true
        isBusy = false
    }

    suspend fun signOut() {
        isBusy = true
        delay(2000)
        isLoggedIn = false
        isBusy = false
    }
}

val UserState = compositionLocalOf<UserStateViewModel> { error("User State Context Not Found!") }