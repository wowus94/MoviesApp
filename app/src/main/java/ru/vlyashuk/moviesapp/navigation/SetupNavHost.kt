package ru.vlyashuk.moviesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.vlyashuk.moviesapp.MainViewModel
import ru.vlyashuk.moviesapp.screens.DetailsScreen
import ru.vlyashuk.moviesapp.screens.MainScreen
import ru.vlyashuk.moviesapp.screens.SplashScreen
import ru.vlyashuk.moviesapp.utils.Constants

sealed class Screens(val route: String) {
    object SplashScreen : Screens(route = Constants.Screens.SPLASH_SCREEN)
    object MainScreen : Screens(route = Constants.Screens.MAIN_SCREEN)
    object DetailsScreen : Screens(route = Constants.Screens.DETAILS_SCREEN)
}

@Composable
fun SetupNavHost(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(navController = navController, startDestination = Screens.SplashScreen.route) {
        composable(route = Screens.SplashScreen.route) {
            SplashScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screens.MainScreen.route) {
            MainScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screens.DetailsScreen.route + "/{Id}") { backStackEntry ->
            DetailsScreen(
                navController = navController,
                viewModel = viewModel,
                itemId = backStackEntry.arguments?.getString("Id") ?: "1"
            )
        }
    }
}