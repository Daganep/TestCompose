package com.example.testcompose.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.testcompose.app.presentation.MainViewModel
import com.example.testcompose.app.presentation.screens.DetailsScreen
import com.example.testcompose.app.presentation.screens.MainScreen
import com.example.testcompose.app.presentation.screens.SplashScreen
import com.example.testcompose.app.utils.Screens.DETAILS_SCREEN
import com.example.testcompose.app.utils.Screens.MAIN_SCREEN
import com.example.testcompose.app.utils.Screens.SPLASH_SCREEN

sealed class Screens(val route: String)
object Splash: Screens(route = SPLASH_SCREEN)
object Main: Screens(route = MAIN_SCREEN)
object Details: Screens(route = DETAILS_SCREEN)

@Composable
fun SetupNavHost(navController: NavHostController, viewModel: MainViewModel) {

    NavHost(
        navController = navController,
        startDestination = Splash.route
    ) {
        composable(route = Splash.route) {
            SplashScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Main.route) {
            MainScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Details.route + "/{id}") { backStackEntry ->
            DetailsScreen(viewModel = viewModel, itemId = backStackEntry.arguments?.getString("id") ?: "1")
        }
    }
}