package com.example.testcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.testcompose.screens.MainScreen
import com.example.testcompose.screens.SplashScreen
import com.example.testcompose.utils.Screens.DETAILS_SCREEN
import com.example.testcompose.utils.Screens.MAIN_SCREEN
import com.example.testcompose.utils.Screens.SPLASH_SCREEN

sealed class Screens(val route: String)
object Splash: Screens(route = SPLASH_SCREEN)
object Main: Screens(route = MAIN_SCREEN)
object Details: Screens(route = DETAILS_SCREEN)

@Composable
fun SetupNavHost(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Splash.route
    ) {
        composable(route = Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Main.route) {
            MainScreen()
        }
        composable(route = Details.route) {

        }
    }
}