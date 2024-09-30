package com.example.locusmobile.ui.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState: StateFlow<LoginUIState> = _uiState.asStateFlow()

    fun updateUsernameField(username: String): Unit {
        _uiState.update { currentUIState -> currentUIState.copy(username = username) }
    }

    fun updatePasswordField(password: String): Unit {
        _uiState.update { currentUIState -> currentUIState.copy(password = password) }
    }
}