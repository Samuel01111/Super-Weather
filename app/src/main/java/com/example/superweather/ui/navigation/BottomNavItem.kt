package com.example.superweather.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    var title: String,
    var icon: ImageVector,
    var screenRoute: String
) {
    object Home : BottomNavItem("Home", Icons.Default.Home,"home")
    object Search: BottomNavItem("Search", Icons.Default.Search,"search")
    object Weathers: BottomNavItem("Weathers", Icons.Default.Menu,"weathers")
    object Settings: BottomNavItem("Settings", Icons.Default.Menu,"settings")
}
