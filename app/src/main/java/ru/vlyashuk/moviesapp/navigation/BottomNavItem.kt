package ru.vlyashuk.moviesapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PregnantWoman
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Home : BottomNavItem("Home", Icons.Default.Home, "Main")
    object Search : BottomNavItem("Search", Icons.Default.Search, "Search")
    object Profile : BottomNavItem("Profile", Icons.Default.PregnantWoman, "Profile")
}
