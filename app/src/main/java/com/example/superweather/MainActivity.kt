package com.example.superweather

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.superweather.ui.MainViewModel
import com.example.superweather.ui.di.MainComponent
import com.example.superweather.ui.navigation.BottomNavItem
import com.example.superweather.ui.theme.SuperWeatherTheme
import com.example.superweather.ui.weather.HomeScreen
import com.example.superweather.ui.weather.SearchScreen
import com.example.superweather.ui.weather.SettingsScreen
import com.example.superweather.ui.weather.WeathersScreen
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    private lateinit var mainComponent: MainComponent

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<MainViewModel> { viewModelFactory }

    @SuppressLint("DiscouragedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        val resId = resources.getIdentifier("weather-cloudynight", "raw", packageName)

        mainComponent = (applicationContext as WeatherApplication)
            .appComponent
            .mainComponent()
            .create()
        
        super.onCreate(savedInstanceState)
        setContent {
            SuperWeatherTheme {
                MainScreenView()
            }
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainScreenView() {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = { BottomNavigation(navController = navController) }
        ) {
            NavigationGraph(navController = navController)
        }
    }

    @Composable
    fun NavigationGraph(navController: NavHostController) {
        NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
            composable(BottomNavItem.Home.screen_route) {
                HomeScreen(
                    backgroundImage = R.drawable.clounds,
                    weatherState = viewModel.state,
                    resId = 1
                )
            }
            composable(BottomNavItem.Search.screen_route) {
                SearchScreen(
                    weatherState = viewModel.state,
                    submit = { viewModel.fetchWeatherByName(it) }
                )
            }
            composable(BottomNavItem.Weathers.screen_route) {
                WeathersScreen()
            }
            composable(BottomNavItem.Settings.screen_route) {
                SettingsScreen()
            }
        }
    }

    @Composable
    fun BottomNavigation(navController: NavController) {
        val items = listOf(
            BottomNavItem.Home,
            BottomNavItem.Search,
            BottomNavItem.Weathers,
            BottomNavItem.Settings
        )
        BottomAppBar(
            modifier = Modifier
                .background(colorResource(id = R.color.teal_200)),
            contentColor = Color.Black
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            items.forEach { item ->
                NavigationBarItem(
                    icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                    label = { Text(text = item.title,
                        fontSize = 9.sp) },
                    alwaysShowLabel = true,
                    selected = currentRoute == item.screen_route,
                    onClick = {
                        navController.navigate(item.screen_route) {

                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Black,
                        unselectedIconColor = Color.Black.copy(0.4f)
                    )
                )
            }
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        mainComponent
            .inject(this)
    }
}
