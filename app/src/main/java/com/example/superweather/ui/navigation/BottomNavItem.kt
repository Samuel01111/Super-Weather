package com.example.superweather.ui.navigation

import com.leumas.superweather.R

sealed class BottomNavItem(
    var title: String,
    var icon: Int,
    var screenRoute: String
) {
    object Home : BottomNavItem("Home", R.drawable.ic_home,"home")
    object Search: BottomNavItem("Search", R.drawable.ic_search,"search")
    object Weathers: BottomNavItem("Weathers", R.drawable.ic_favorite,"weathers")
    object Settings: BottomNavItem("Settings", R.drawable.ic_settings,"settings")
}
