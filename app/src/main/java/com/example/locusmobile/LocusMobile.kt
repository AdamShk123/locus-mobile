package com.example.locusmobile

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.locusmobile.ui.HomeScreen
import com.example.locusmobile.ui.LoginScreen
import com.example.locusmobile.ui.theme.LocusMobileTheme

enum class Screen(@StringRes val title: Int) {
    Login(title = R.string.login_screen_title),
    Home(title = R.string.home_screen_title)
}

@Composable
fun LocusMobile(modifier: Modifier = Modifier) {
    val navController: NavHostController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = Screen.valueOf(
        backStackEntry?.destination?.route ?: Screen.Login.name
    )

    LocusMobileTheme {
        Scaffold(
            topBar = { TopNavigationBar(currentScreen = currentScreen) }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Screen.Login.name,
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                composable(Screen.Login.name) {
                    LoginScreen(onLoginButtonClicked = { navController.navigate(Screen.Home.name)})
                }
                composable(Screen.Home.name) {
                    HomeScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavigationBar(modifier: Modifier = Modifier, currentScreen: Screen) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults
            .topAppBarColors()
            .copy(containerColor = colorResource(R.color.red)),
        title = {
            Text(
                text = stringResource(currentScreen.title),
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.white)
            )
        },
        navigationIcon = {
            if(currentScreen != Screen.Login)
                IconButton(
                    onClick = {},
                    colors = IconButtonDefaults
                        .iconButtonColors()
                        .copy(
                            contentColor = colorResource(R.color.white)
                        )
                ) {
                    Icon(Icons.Filled.Menu, contentDescription = "Action Icon")
                }
        },
        actions = {
            if(currentScreen != Screen.Login)
                IconButton(
                    onClick = {},
                    colors = IconButtonDefaults
                        .iconButtonColors()
                        .copy(
                            contentColor = colorResource(R.color.white)
                        )
                ) {
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