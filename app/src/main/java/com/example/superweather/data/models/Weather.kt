package com.example.superweather.data.models

data class Weather(
    val id: Long,
    val location: String,
    val temperature: String,
    val condition: String,
    val high: String,
    val low: String
)
