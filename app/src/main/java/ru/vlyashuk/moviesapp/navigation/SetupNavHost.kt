package ru.vlyashuk.moviesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.vlyashuk.moviesapp.MainViewModel
import ru.vlyashuk.moviesapp.screens.MainScreen
import ru.vlyashuk.moviesapp.screens.SplashScreen
import ru.vlyashuk.moviesapp.utils.Constants

sealed class Screens(val route: String) {
    object Splash : Screens(route = Constants.Screens.SPLASH_SCREEN)
    object Main : Screens(route = Constants.Screens.MAIN_SCREEN)
}

@Composable
fun SetupNavHost(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(navController = navController, startDestination = Screens.Splash.route) {
        composable(route = Screens.Splash.route) {
            SplashScreen(navController = navController, viewModel= viewModel)
        }
        composable(route = Screens.Main.route) {
            MainScreen(navController = navController, viewModel= viewModel)
        }
    }
}