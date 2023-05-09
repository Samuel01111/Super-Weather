package com.example.superweather.ui.weather

import com.example.superweather.data.models.Weather

data class WeatherState(
    val weatherInfo: Weather,
    val isLoading: Boolean = false,
    val error: String? = null
)
