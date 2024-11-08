package com.example.superweather

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.superweather.ui.FavoritesViewModel
import com.example.superweather.ui.MainViewModel
import com.example.superweather.ui.di.MainComponent
import com.example.superweather.ui.navigation.BottomNavItem
import com.example.superweather.ui.screens.HomeScreen
import com.example.superweather.ui.screens.SearchScreen
import com.example.superweather.ui.screens.SplashScreen
import com.example.superweather.ui.screens.WeathersScreen
import com.example.superweather.ui.screens.components.DialogError
import com.example.superweather.ui.theme.BlueGood
import com.example.superweather.ui.theme.SuperWeatherTheme
import com.leumas.superweather.R
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    private lateinit var mainComponent: MainComponent
    private var isPermissionRequestPending = false

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<MainViewModel> { viewModelFactory }

    private val viewModelFavorites by viewModels<FavoritesViewModel> { viewModelFactory }

    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        mainComponent = (applicationContext as WeatherApplication)
            .appComponent
            .mainComponent()
            .create()

        mainComponent.inject(this)
        super.onCreate(savedInstanceState)
        setupPermissionLauncher()
        setContent {
            SuperWeatherTheme {
                MainScreenView()
            }
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun MainScreenView() {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = {
                BottomNavigation(
                    navController = navController,
                    isVisible = viewModel.isBottomBarVisible
                )
            }
        ) {
            NavigationGraph(navController = navController)
        }
    }

    @Composable
    fun NavigationGraph(navController: NavHostController) {
        if (viewModel.isDialogErrorVisible) {
            Box {
                DialogError(
                    title = stringResource(id = R.string.error_exception_generic_title),
                    message = stringResource(id = R.string.error_exception_generic),
                    textConfirmButton = stringResource(id = R.string.common_ok),
                    onDismissDialog = { viewModel.onDismissDialog() }
                )
            }
        }
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .background(BlueGood),
            navController = navController,
            startDestination = "splash_screen"
        ) {
            composable("splash_screen") { SplashScreen(navController) }
            composable(BottomNavItem.Home.screenRoute) {
                HomeScreen(
                    weatherState = viewModel.currentHome,
                    onWeatherInfoEmptyButtonClick = { viewModel.onWeatherInfoEmptyButtonClick() }
                )
            }
            composable(BottomNavItem.Search.screenRoute) {
                SearchScreen(
                    searchState = viewModel.searchState,
                    currentLocationState = viewModel.currentLocationState,
                    submit = { viewModel.fetchWeatherByName(it) },
                    onSearchLocationRowClicked = { viewModel.onSearchLocationRowClicked() },
                    onCurrentLocationRowClicked = {
                        viewModel.onCurrentLocationRowClicked()
                        goToHome(navController)
                    },
                    onRequestLocalizationPermissionClicked = { openSettings() },
                    isLocationPermissionActive = viewModel.isLocationPermissionActive,
                    isSearching = viewModel.isSearching,
                    isOpenedBottomSheet = viewModel.isOpenedBottomSheet,
                    onDismissBottomSheetRequest = { viewModel.onDismissBottomSheetRequest() },
                    clearError = { viewModel.clearError() },
                    isWeatherInfoEmpty = viewModel.searchState.isWeatherInfoEmpty,
                    onWeatherInfoEmptyButtonClick = { viewModel.onWeatherInfoEmptyButtonClick() }
                )
            }
            composable(BottomNavItem.Weathers.screenRoute) {
                WeathersScreen(
                    favoriteState = viewModelFavorites.favoriteState,
                    weatherState = viewModel.favoriteState,
                    isOpenedBottomSheet = viewModel.isOpenedBottomSheet,
                    onRefresh = { viewModelFavorites.fetchFavorites() },
                    onRemoveItemClicked = { viewModelFavorites.removeFavorite(it) },
                    onItemClicked = {
                        viewModel.onSearchLocationRowClicked()
                        viewModel.updateFavoriteState(viewModelFavorites.fetchFavorite(it))
                    },
                    onWeatherInfoEmptyButtonClick = { viewModel.onWeatherInfoEmptyButtonClick() },
                    onDismissBottomSheetRequest = { viewModel.onDismissBottomSheetRequest() }
                )
            }
        }
    }

    @Composable
    fun BottomNavigation(
        navController: NavController,
        isVisible: Boolean = false
    ) {
        val tabsItems = listOf(
            BottomNavItem.Home,
            BottomNavItem.Search,
            BottomNavItem.Weathers
        )
        if (isVisible) {
            BottomAppBar(
                modifier = Modifier
                    .background(colorResource(id = R.color.teal_200))
                    .height(64.dp),
                contentColor = Color.Black,
                containerColor = Color.White,
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val currentFraction = 1f / tabsItems.size

                LazyRow {
                    items(tabsItems) { item ->
                        NavigationBarItem(
                            modifier = Modifier.fillParentMaxWidth(currentFraction),
                            icon = { Icon(
                                modifier = Modifier.size(28.dp),
                                imageVector = item.icon,
                                contentDescription = item.title,
                                tint = MaterialTheme.colorScheme.secondary
                            ) },
                            label = {},
                            alwaysShowLabel = true,
                            selected = currentRoute == item.screenRoute,
                            onClick = {
                                navController.navigate(item.screenRoute)
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.Black,
                                unselectedIconColor = Color.Black.copy(0.4f),
                                indicatorColor = BlueGood.copy(0.8f),
                                disabledIconColor = Color.Black,
                            )
                        )
                    }
                }
            }
        }
    }

    @Composable
    @Preview
    fun BottomNavigationPreview() {
        val navController = NavController(this)
        val isVisible = true
        BottomNavigation(navController = navController, isVisible = isVisible)
    }

    private fun goToHome(navController: NavHostController) {
        navController.navigate(
            route = BottomNavItem.Home.screenRoute
        )
    }

    private fun openSettings() {
        val intent = Intent(
            ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", packageName, null))
        startActivityForResult(intent, REQUEST_CODE_SETTINGS)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_SETTINGS) {
            permissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    private fun setupPermissionLauncher() {
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
            ) {
                // Permissions granted, fetch weather by localization
                viewModel.fetchWeatherByLocalization()
                //Timber.d("Permission Granted by the user.")
            } else {
                // Permissions denied, handle the case here if needed
                viewModel.fetchWeatherByName("nova york")
               // Timber.d("Permission Denied by the user.")
            }
        }

        if (!isPermissionRequestPending) {
            isPermissionRequestPending = true
            permissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_NETWORK_STATE
                )
            )
        }
    }

    companion object {
        private const val REQUEST_CODE_SETTINGS = 1002
    }
}
