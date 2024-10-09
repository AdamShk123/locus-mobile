package com.example.locusmobile

import com.example.locusmobile.ui.login.LoginViewModel
import org.junit.Test

class LoginViewModelTest {
    private val viewModel = LoginViewModel()

    @Test
    fun loginViewModel_objectInstantiated_variablesSet() {
        assert(viewModel.username == "")
        assert(viewModel.password == "")

        val currentState = viewModel.uiState.value
        assert(currentState.areFieldsEmpty)
        assert(!currentState.isLoginIncorrect)
    }

    @Test
    fun loginViewModel_updatedUsernameField_usernameVariableUpdated() {
        val previousValue = viewModel.username

        viewModel.updateUsernameField("username")

        val currentValue = viewModel.username

        assert(previousValue != currentValue)
        assert(currentValue == "username")
    }

    @Test
    fun loginViewModel_updatedPasswordField_passwordVariableUpdated() {
        val previousValue = viewModel.password

        viewModel.updatePasswordField("password")

        val currentValue = viewModel.password

        assert(previousValue != currentValue)
        assert(currentValue == "password")
    }

    @Test
    fun loginViewModel_updatedOneField_variableAreFieldsEmptyStillTrue() {
        viewModel.updateUsernameField("username")
        viewModel.checkFields()

        val currentState = viewModel.uiState.value
        assert(currentState.areFieldsEmpty)
    }

    @Test
    fun loginViewModel_updatedBothFields_variableAreFieldsEmptyFalse() {
        viewModel.updateUsernameField("username")
        viewModel.updatePasswordField("password")
        viewModel.checkFields()

        val currentState = viewModel.uiState.value

        assert(!currentState.areFieldsEmpty)
    }

    @Test
    fun loginViewModel_incorrectLogin_variableSetTrue() {
        viewModel.updateIncorrectLogin()

        val currentState = viewModel.uiState.value

        assert(currentState.isLoginIncorrect)
    }
}