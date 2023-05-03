package com.example.superweather.data.repository

import com.example.superweather.data.db.WeatherEntity
import com.example.superweather.data.models.Weather

interface WeatherRepository {

    suspend fun saveWeather(weather: Weather)

    suspend fun updateWeather(entity: WeatherEntity)

    suspend fun removeWeatherById(id: Int)

    suspend fun getWeathers(): List<Weather>

    suspend fun getWeatherById(id: Int): Weather
}