package com.example.superweather.data.remote

import com.squareup.moshi.Json

data class WeatherCondition(
    @field:Json(name = "main")
    val condition: String,
    val description: String,
    val icon: String
)
