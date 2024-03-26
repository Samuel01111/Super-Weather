package com.example.superweather.data.repository

import com.example.superweather.data.models.Weather
import com.example.superweather.data.utils.Resource

interface WeatherAPIRepository {

    suspend fun getWeatherByName(name: String): Resource<Weather>

    suspend fun getWeatherByLocalization(lat: Double, long: Double): Resource<Weather>
}
        