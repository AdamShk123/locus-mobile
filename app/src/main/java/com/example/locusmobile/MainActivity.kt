package com.example.locusmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.locusmobile.ui.theme.LocusMobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LocusMobile()
        }
    }
}

@Composable
fun LocusMobile(modifier: Modifier = Modifier) {
    LocusMobileTheme {
        Scaffold(
            modifier = modifier.fillMaxSize(),
            topBar = { TopNavigationBar() }
        ) { innerPadding ->
            Content(modifier = modifier.padding(innerPadding))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavigationBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults
            .topAppBarColors()
            .copy(containerColor = colorResource(R.color.red)),
        title = { Text(text = stringResource(R.string.login_screen_title)) },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Menu, contentDescription = "Action Icon")
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Person, contentDescription = "Action Icon")
            }
        }
    )
}

@Composable
fun Content(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(R.color.white)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card()
        Logo()
    }
}

@Composable
fun Card(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxHeight(.8f)
            .fillMaxWidth()
            .padding(16.dp)
            .background(colorResource(R.color.light_gray)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        InputContainer()
        DescriptionContainer()
    }
}

@Composable
fun ColumnScope.InputContainer(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.weight(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        FieldContainer()
        ButtonContainer()
    }
}

@Composable
fun ColumnScope.FieldContainer(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .weight(1f)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        TextField(
            label = {Text(text = stringResource(R.string.login_screen_username_field))},
            value = "",
            onValueChange = {},
            trailingIcon = {
                Icon(Icons.Filled.Close, contentDescription = "Action Icon")
            },
            colors = TextFieldDefaults
                .colors()
                .copy(
                    unfocusedContainerColor = colorResource(R.color.dark_gray),
                    focusedContainerColor = colorResource(R.color.dark_gray),
                    focusedIndicatorColor = colorResource(R.color.yellow)
                )
        )
        TextField(
            label = {Text(text = stringResource(R.string.login_screen_password_field))},
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
                )
        )
    }
}

@Composable
fun ButtonContainer(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextButton(
            onClick = {},
            shape = RectangleShape,
            colors = ButtonDefaults
                .textButtonColors()
                .copy(contentColor = colorResource(R.color.red))
        ) {
            Text(
                text = stringResource(R.string.login_screen_forgot_password_button),
                modifier = modifier
            )
        }
        Button(
            onClick = {},
            shape = RectangleShape,
            colors = ButtonDefaults
                .buttonColors()
                .copy(
                    containerColor = colorResource(R.color.red),
                    contentColor = colorResource(R.color.black)
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
fun LocusMobilePreview() {
    LocusMobile()
}