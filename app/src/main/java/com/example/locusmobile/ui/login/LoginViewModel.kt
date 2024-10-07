package com.example.locusmobile.ui.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.locusmobile.TAG
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LoginState())
    val uiState: StateFlow<LoginState> = _uiState.asStateFlow()

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

    fun checkFields(): Unit {
        val result = checkFieldsEmpty()
        Log.d(TAG,"checkFields() called, result: $result")
        _uiState.update { currentState ->
            currentState.copy(areFieldsEmpty = result)
        }
    }

    private fun checkFieldsEmpty(): Boolean {
        return username.isEmpty() || password.isEmpty()
    }

    fun updateIncorrectLogin(): Unit {
        _uiState.update { currentState ->
            currentState.copy(isLoginIncorrect = true)
        }
    }
}