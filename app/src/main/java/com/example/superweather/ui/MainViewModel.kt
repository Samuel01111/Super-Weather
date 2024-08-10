package com.example.superweather.ui

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superweather.data.mapper.getLocalDateTime
import com.example.superweather.data.mapper.getWeatherDetailsItems
import com.example.superweather.data.mapper.getWeatherRowViewEntity
import com.example.superweather.data.repository.WeatherAPIRepository
import com.example.superweather.data.repository.WeatherDatabaseRepository
import com.example.superweather.data.utils.Resource
import com.example.superweather.data.utils.getEmptyWeather
import com.example.superweather.domain.location.LocationTracker
import com.example.superweather.ui.screens.WeatherState
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainViewModel @Inject constructor(
    val repository: WeatherAPIRepository,
    val repositoryDatabase: WeatherDatabaseRepository,
    private val locationTracker: LocationTracker,
    val context: Context
) : ViewModel() {
    var isLocationPermissionActive by mutableStateOf(false)

    var isBottomBarVisible by mutableStateOf(false)
    var isDialogErrorVisible by mutableStateOf(false)
    var isSearching by mutableStateOf(false)
    var isOpenedBottomSheet by mutableStateOf(false)

    var currentHome by mutableStateOf(WeatherState(getEmptyWeather()))
        private set

    var currentLocationState by mutableStateOf(WeatherState(getEmptyWeather()))
        private set

    var searchState by mutableStateOf(WeatherState(getEmptyWeather()))

    fun fetchWeatherByName(cityName: String) {
        isSearching = true
        viewModelScope.launch {
            when(val resource = repository.getWeatherByName(cityName.trim())) {
                is Resource.Success -> {
                    searchState = searchState.copy(
                        weatherInfo = resource.data,
                        isLoading = false,
                        isCurrentLocation = false,
                        error = null,
                        date = getLocalDateTime(),
                        weatherRowViewEntity = getWeatherRowViewEntity(resource.data),
                        weatherItems = getWeatherDetailsItems(resource.data, context),
                        isWeatherInfoEmpty = false

                    )
                    currentHome = searchState
                    isBottomBarVisible = true
                    isSearching = false
                    repositoryDatabase.saveWeather(resource.data)
                }
                is Resource.Dialog -> {
                    searchState = searchState.copy(
                        weatherInfo = getEmptyWeather(),
                        isLoading = false,
                        isWeatherInfoEmpty = true,
                        error = resource.message
                    )
                    currentHome = searchState
                    isDialogErrorVisible = true
                    isBottomBarVisible = true
                    isSearching = false
                }
                is Resource.Error -> {
                    searchState = searchState.copy(
                        weatherInfo = getEmptyWeather(),
                        isLoading = false,
                        isWeatherInfoEmpty = false,
                        error = resource.message
                    )
                    isBottomBarVisible = true
                    isSearching = false
                }
            }
        }
    }

    fun fetchWeatherByLocalization() {
        isSearching = true
        viewModelScope.launch {
            locationTracker.getCurrentLocation()?.let {
                isLocationPermissionActive = true
                when(val resource = repository.getWeatherByLocalization(it.latitude, it.longitude)) {
                    is Resource.Success -> {
                        currentLocationState = currentLocationState.copy(
                            weatherInfo = resource.data,
                            isLoading = false,
                            isCurrentLocation = true,
                            error = null,
                            date = getLocalDateTime(),
                            weatherRowViewEntity = getWeatherRowViewEntity(resource.data),
                            weatherItems = getWeatherDetailsItems(resource.data, context),
                            isWeatherInfoEmpty = false
                        )
                        currentHome = currentLocationState
                        isBottomBarVisible = true
                        isSearching = false
                        repositoryDatabase.saveWeather(resource.data)
                    }
                    is Resource.Dialog -> {
                        currentLocationState = currentLocationState.copy(
                            weatherInfo = getEmptyWeather(),
                            isLoading = false,
                            isWeatherInfoEmpty = true,
                            error = resource.message
                        )
                        currentHome = currentLocationState
                        isDialogErrorVisible = true
                        isBottomBarVisible = true
                        isSearching = false
                    }
                    is Resource.Error -> {
                        currentLocationState = currentLocationState.copy(
                            weatherInfo = getEmptyWeather(),
                            isLoading = false,
                            isWeatherInfoEmpty = false,
                            error = resource.message
                        )
                        isBottomBarVisible = true
                        isSearching = false
                    }
                }
            } ?: kotlin.run {
                fetchWeatherByName("Osasco")
                isBottomBarVisible = true
            }
        }
    }

    fun onSearchLocationRowClicked() {
        isOpenedBottomSheet = true
    }

    fun onDismissBottomSheetRequest() {
        isOpenedBottomSheet = false
    }

    fun onDismissDialog() {
        isDialogErrorVisible = false
    }

    fun onCurrentLocationRowClicked() {
        currentHome = currentLocationState
    }

    fun clearError() {
        searchState = searchState.copy(error = null)
    }

    fun onWeatherInfoEmptyButtonClick() {
        fetchWeatherByLocalization()
    }
}
