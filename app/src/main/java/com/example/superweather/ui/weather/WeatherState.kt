package com.example.superweather.ui.weather

import com.airbnb.lottie.compose.LottieCompositionSpec
import com.example.superweather.data.models.Weather

data class WeatherState(
    val weatherInfo: Weather,
    val isLoading: Boolean = false,
    val error: String? = null,
    val weatherRowViewEntity: WeatherRowViewEntity? = null
)
data class WeatherRowViewEntity(
    val location: String,
    val temperature: String,
    val icon: LottieCompositionSpec.RawRes,
)

