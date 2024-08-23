package com.example.superweather.ui.screens

import androidx.compose.ui.graphics.Color
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.example.superweather.data.models.Weather
import java.util.Date

data class WeatherState(
    val weatherInfo: Weather,
    val isLoading: Boolean = true,
    val isCurrentLocation: Boolean = false,
    val isWeatherInfoEmpty: Boolean = false,
    val error: String? = null,
    val date: String? = null,
    val weatherRowViewEntity: WeatherRowViewEntity? = null,
    val weatherItems: List<WeatherDetailsViewEntity>? = null
)

data class FavoriteState(
    val weatherRows: List<WeatherRowViewEntity>,
    val isLoading: Boolean
)

data class WeatherRowViewEntity(
    val id: Long,
    val location: String,
    val temperature: String,
    val icon: LottieCompositionSpec.RawRes,
    val backgroundColor: Color,
    val date: Date
)

data class WeatherDetailsViewEntity(
    val title: String,
    val value: String,
    val icon: LottieCompositionSpec.RawRes
)
