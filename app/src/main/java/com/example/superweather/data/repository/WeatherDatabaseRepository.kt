package com.example.superweather.data.repository

import com.example.superweather.data.models.Weather

interface WeatherDatabaseRepository {

    suspend fun saveWeather(weather: Weather)

    suspend fun removeWeatherById(id: Long): Int

    suspend fun getWeathers(): List<Weather>

    suspend fun getWeatherById(id: Long): Weather
}
