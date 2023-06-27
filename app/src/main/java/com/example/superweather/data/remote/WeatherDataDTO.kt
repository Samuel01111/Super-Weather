package com.example.superweather.data.remote

import com.squareup.moshi.Json

data class WeatherDataDTO(
    val id: Long,
    val name: String,
    @field:Json(name = "weather")
    val weatherCondition: List<WeatherCondition>,
    @field:Json(name = "main")
    val weatherValues: WeatherValues,
    @field:Json(name = "sys")
    val weatherSys: WeatherSystem,
    @field:Json(name = "wind")
    val wind: WeatherWind
)
