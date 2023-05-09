package com.example.superweather.data.remote

data class WeatherSystem(
    val id: Int,
    val type: Int,
    val country: String,
    val sunrise: Int,
    val sunset: Int
)
