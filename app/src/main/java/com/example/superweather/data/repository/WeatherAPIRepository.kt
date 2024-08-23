package com.example.superweather.data.repository

import com.example.superweather.data.models.Weather
import com.example.superweather.data.models.Weathers
import com.example.superweather.data.utils.Resource

interface WeatherAPIRepository {
    suspend fun getWeatherByName(name: String): Resource<Weather>
    suspend fun getWeatherByLocalization(lat: Double, long: Double): Resource<Weather>
    suspend fun getWeathers(names: List<String>): Resource<Weathers>
}
        