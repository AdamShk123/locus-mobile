package com.example.locusmobile.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.locusmobile.R
import com.example.locusmobile.ui.UserState
import com.example.locusmobile.ui.theme.LocusMobileTheme
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(modifier: Modifier = Modifier, loginViewModel: LoginViewModel, onLoginButtonClicked: () -> Unit) {
    val uiState = loginViewModel.uiState.collectAsState()
    val username = loginViewModel.username
    val password = loginViewModel.password

    val userViewModel = UserState.current
    val coroutineScope = rememberCoroutineScope()

    LoginScreen(modifier = modifier,
        uiState = uiState,
        username = username,
        password = password,
        onLoginButtonClicked = {
            coroutineScope.launch {
                userViewModel.signIn(username,password)
                if(userViewModel.isLoggedIn) {
                    onLoginButtonClicked()
                }
                else {
                    loginViewModel.updateIncorrectLogin()
                }
            }
        },
        onKeyboardDone = { loginViewModel.checkFields() },
        onUsernameFieldUpdated = { loginViewModel.updateUsernameField(it) },
        onPasswordFieldUpdated = { loginViewModel.updatePasswordField(it) }
    )
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    uiState: State<LoginState>,
    username: String,
    password: String,
    onLoginButtonClicked: () -> Unit,
    onKeyboardDone: () -> Unit,
    onUsernameFieldUpdated: (String) -> Unit,
    onPasswordFieldUpdated: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .clickable(
                onClickLabel = stringResource(R.string.login_screen_composable),
                interactionSource = interactionSource,
                indication = null)
            {
                focusManager.clearFocus()
                keyboardController?.hide()
            }
            .background(colorResource(R.color.white)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            uiState = uiState,
            onKeyboardDone = onKeyboardDone,
            username = username,
            password = password,
            onLoginButtonClicked = onLoginButtonClicked,
            onUsernameFieldUpdated = onUsernameFieldUpdated,
            onPasswordFieldUpdated = onPasswordFieldUpdated
        )
        Logo()
    }
}

@Composable
fun Card(
    modifier: Modifier = Modifier,
    onLoginButtonClicked: () -> Unit,
    onUsernameFieldUpdated: (String) -> Unit,
    onPasswordFieldUpdated: (String) -> Unit,
    password: String,
    username: String,
    uiState: State<LoginState>,
    onKeyboardDone: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxHeight(.8f)
            .fillMaxWidth()
            .padding(16.dp)
            .background(colorResource(R.color.light_gray)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        InputContainer(
            uiState = uiState,
            username = username,
            password = password,
            onKeyboardDone = onKeyboardDone,
            onLoginButtonClicked = onLoginButtonClicked,
            onUsernameFieldUpdated = onUsernameFieldUpdated,
            onPasswordFieldUpdated = onPasswordFieldUpdated
        )
        DescriptionContainer()
    }
}

@Composable
fun ColumnScope.InputContainer(
    modifier: Modifier = Modifier,
    onLoginButtonClicked: () -> Unit,
    onUsernameFieldUpdated: (String) -> Unit,
    onPasswordFieldUpdated: (String) -> Unit,
    username: String,
    password: String,
    uiState: State<LoginState>,
    onKeyboardDone: () -> Unit
) {
    Column(
        modifier = modifier.weight(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        FieldContainer(
            username = username,
            password = password,
            isLoginIncorrect = uiState.value.isLoginIncorrect,
            onUsernameFieldUpdated = onUsernameFieldUpdated,
            onPasswordFieldUpdated = onPasswordFieldUpdated,
            onKeyboardDone = onKeyboardDone
        )
        ButtonContainer(
            isLoginButtonEnabled = !uiState.value.areFieldsEmpty,
            onLoginButtonClicked = onLoginButtonClicked
        )
    }
}

@Composable
fun ColumnScope.FieldContainer(
    modifier: Modifier = Modifier,
    onUsernameFieldUpdated: (String) -> Unit,
    onPasswordFieldUpdated: (String) -> Unit,
    username: String,
    password: String,
    onKeyboardDone: () -> Unit,
    isLoginIncorrect: Boolean,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val onFocusChanged: (FocusState) -> Unit = { focusState ->
        if(focusState.isFocused) {
            keyboardController?.show()
        }
        else {
            keyboardController?.hide()
            onKeyboardDone()
        }
    }

    val onDone: (KeyboardActionScope.() -> Unit)= {
        keyboardController?.hide()
        focusManager.clearFocus()
        onKeyboardDone()
    }

    Column(
        modifier = modifier
            .weight(1f)
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        if(isLoginIncorrect)
            Text(
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.red),
                text = stringResource(R.string.login_screen_login_incorrect)
            )
        TextField(
            label = { Text(text = stringResource(R.string.login_screen_username_field)) },
            value = username,
            onValueChange = onUsernameFieldUpdated,
            singleLine = true,
            trailingIcon = {
                Icon(Icons.Filled.Close, contentDescription = "Action Icon")
            },
            colors = TextFieldDefaults
                .colors()
                .copy(
                    unfocusedContainerColor = colorResource(R.color.dark_gray),
                    focusedContainerColor = colorResource(R.color.dark_gray),
                    focusedIndicatorColor = colorResource(R.color.yellow)
                ),
            modifier = modifier
                .fillMaxWidth()
                .onFocusChanged(onFocusChanged = onFocusChanged),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = onDone
            )
        )
        TextField(
            label = { Text(text = stringResource(R.string.login_screen_password_field)) },
            value = password,
            onValueChange = onPasswordFieldUpdated,
            singleLine = true,
            trailingIcon = {
                Icon(Icons.Filled.Visibility, contentDescription = "Action Icon")
            },
            colors = TextFieldDefaults
                .colors()
                .copy(
                    unfocusedContainerColor = colorResource(R.color.dark_gray),
                    focusedContainerColor = colorResource(R.color.dark_gray),
                    focusedIndicatorColor = colorResource(R.color.yellow)
                ),
            modifier = modifier
                .fillMaxWidth()
                .onFocusChanged(onFocusChanged = onFocusChanged),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = onDone
            )
        )
    }
}

@Composable
fun ButtonContainer(
    modifier: Modifier = Modifier,
    onLoginButtonClicked: () -> Unit,
    isLoginButtonEnabled: Boolean
)
{
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextButton(
            onClick = {},
            shape = RectangleShape,
            colors = ButtonDefaults
                .textButtonColors()
                .copy(contentColor = Color(0xFFB00000))
        ) {
            Text(
                text = stringResource(R.string.login_screen_forgot_password_button),
                textDecoration = TextDecoration.Underline,
                modifier = modifier
            )
        }
        Button(
            onClick = onLoginButtonClicked,
            enabled = isLoginButtonEnabled,
            shape = RectangleShape,
            colors = ButtonDefaults
                .buttonColors()
                .copy(
                    containerColor = colorResource(R.color.red),
                    contentColor = colorResource(R.color.white)
                )
        ) {
            Text(
                text = stringResource(R.string.login_screen_login_button),
                modifier = modifier
            )
        }
    }
}

@Composable
fun ColumnScope.DescriptionContainer(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .weight(1f)
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AboutContainer()
        ClassesContainer()
    }
}

@Composable
fun ColumnScope.AboutContainer(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .weight(2f)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.login_screen_about_title),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
        )
        Text(
            text = stringResource(R.string.login_screen_about_description),
            modifier = Modifier
        )
    }
}

@Composable
fun ColumnScope.ClassesContainer(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.weight(1f),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.login_screen_classes_title),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
        )
        Text(
            text = stringResource(R.string.login_screen_classes_description),
            modifier = Modifier
        )
    }
}

@Composable
fun Logo(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Loyola Logo",
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun LoginScreenPreview() {
    val loginState = remember { mutableStateOf(LoginState()) }
    LocusMobileTheme {
        LoginScreen(
            uiState = loginState,
            username = "username",
            password = "password",
            onLoginButtonClicked = {},
            onKeyboardDone = {},
            onUsernameFieldUpdated = {},
            onPasswordFieldUpdated = {}
        )
    }
}