package com.example.superweather.data.remote

import com.squareup.moshi.Json

data class WeatherValues(
    @field:Json(name = "temp")
    val temperature: Double,
    @field:Json(name = "feels_like")
    val feelsLike: Double,
    @field:Json(name = "temp_min")
    val minTemp: Double,
    @field:Json(name = "temp_max")
    val maxTemp: Double,
    val pressure: Int?,
    val humidity: Int?
)
