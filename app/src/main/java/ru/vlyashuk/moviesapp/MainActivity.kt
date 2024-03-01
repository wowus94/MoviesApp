package ru.vlyashuk.moviesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import ru.vlyashuk.moviesapp.navigation.SetupNavHost
import ru.vlyashuk.moviesapp.ui.theme.MoviesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesAppTheme {
                val navController = rememberNavController()
                SetupNavHost(navController = navController)
            }
        }
    }
}