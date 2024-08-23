package com.example.superweather.data.models

import java.util.Date

data class Weather(
    val id: Long,
    val date: Date,
    val location: String,
    val temperature: String,
    val condition: String,
    val high: String,
    val low: String,
    val humidity: String?,
    val pressure: String?,
    val speed: String?,
    val deg: String?
)

data class Weathers(
    val weathers: List<Weather>
)
