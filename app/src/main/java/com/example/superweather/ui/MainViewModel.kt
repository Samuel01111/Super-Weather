package com.example.superweather.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superweather.data.repository.WeatherAPIRepository
import com.example.superweather.data.utils.Resource
import com.example.superweather.data.utils.getEmptyWeather
import com.example.superweather.ui.weather.WeatherState
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    val repository: WeatherAPIRepository
) : ViewModel() {

    var state by mutableStateOf(WeatherState(getEmptyWeather()))
        private set

    fun fetchWeatherByName(cityName: String) {
        viewModelScope.launch {
            when(val resource = repository.getWeatherByName(cityName)) {
                is Resource.Success -> {
                    state = state.copy(
                        weatherInfo = resource.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        weatherInfo = getEmptyWeather(),
                        isLoading = false,
                        error = resource.message
                    )
                }
            }
        }
    }
}