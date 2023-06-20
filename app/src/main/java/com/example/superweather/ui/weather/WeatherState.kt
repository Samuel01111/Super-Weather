package com.example.superweather.ui.weather

import androidx.compose.ui.graphics.Color
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.example.superweather.data.models.Weather

data class WeatherState(
    val weatherInfo: Weather,
    val isLoading: Boolean = true,
    val error: String? = null,
    val date: String? = null,
    val weatherRowViewEntity: WeatherRowViewEntity? = null
)
data class WeatherRowViewEntity(
    val location: String,
    val temperature: String,
    val icon: LottieCompositionSpec.RawRes,
    val backgroundColor: Color
)

data class WeatherDetailsViewEntity(
    val title: String,
    val value: String,
    val icon: LottieCompositionSpec.RawRes
)
