package com.example.locusmobile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.locusmobile.ui.HomeScreen
import com.example.locusmobile.ui.LoginScreen
import com.example.locusmobile.ui.theme.LocusMobileTheme

enum class Screen {
    Login,
    Home
}

@Composable
fun LocusMobile(modifier: Modifier = Modifier) {
    val navController: NavHostController = rememberNavController()

    Scaffold(
        topBar = { TopNavigationBar() }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Login.name,
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(Screen.Login.name) {
                LoginScreen()
            }
            composable(Screen.Home.name) {
                HomeScreen()
            }
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
        title = {
            Text(
                text = stringResource(R.string.login_screen_title),
                fontWeight = FontWeight.Bold
            )
        },
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

@Preview(
    showBackground = true
)
@Composable
fun LocusMobilePreview() {
    LocusMobileTheme {
        LocusMobile()
    }
}