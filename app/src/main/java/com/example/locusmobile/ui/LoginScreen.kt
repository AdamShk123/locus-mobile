package com.example.locusmobile.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.locusmobile.R
import com.example.locusmobile.TAG
import com.example.locusmobile.ui.theme.LocusMobileTheme

@Composable
fun LoginScreen(modifier: Modifier = Modifier, onLoginButtonClicked: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(R.color.white)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(onLoginButtonClicked = onLoginButtonClicked)
        Logo()
    }
}

@Composable
fun Card(modifier: Modifier = Modifier, onLoginButtonClicked: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxHeight(.8f)
            .fillMaxWidth()
            .padding(16.dp)
            .background(colorResource(R.color.light_gray)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        InputContainer(onLoginButtonClicked = onLoginButtonClicked)
        DescriptionContainer()
    }
}

@Composable
fun ColumnScope.InputContainer(modifier: Modifier = Modifier, onLoginButtonClicked: () -> Unit) {
    Column(
        modifier = modifier.weight(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        FieldContainer()
        ButtonContainer(onLoginButtonClicked = onLoginButtonClicked)
    }
}

@Composable
fun ColumnScope.FieldContainer(modifier: Modifier = Modifier) {
    var username by rememberSaveable { mutableStateOf("") }
    Log.d(TAG, "username: $username")
    Column(
        modifier = modifier
            .weight(1f)
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        TextField(
            label = { Text(text = stringResource(R.string.login_screen_username_field)) },
            value = username,
            onValueChange = { username = it},
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
            modifier = modifier.fillMaxWidth()
        )
        TextField(
            label = { Text(text = stringResource(R.string.login_screen_password_field)) },
            value = "",
            onValueChange =  {},
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
            modifier = modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ButtonContainer(modifier: Modifier = Modifier, onLoginButtonClicked: () -> Unit) {
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
        modifier = modifier.weight(2f).fillMaxWidth(),
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
    LocusMobileTheme {
        LoginScreen(onLoginButtonClicked = {})
    }
}