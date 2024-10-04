package com.example.locusmobile.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    var username by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    fun updateUsernameField(username: String): Unit {
        this.username = username
    }

    fun updatePasswordField(password: String): Unit {
        this.password = password
    }
}