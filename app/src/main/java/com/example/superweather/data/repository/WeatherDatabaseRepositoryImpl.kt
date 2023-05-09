package com.example.superweather.data.repository

import com.example.superweather.data.db.dao.WeatherDao
import com.example.superweather.data.mapper.toWeather
import com.example.superweather.data.mapper.toWeatherEntity
import com.example.superweather.data.models.Weather
import javax.inject.Inject

class WeatherDatabaseRepositoryImpl @Inject constructor(
    private val dao: WeatherDao
): WeatherDatabaseRepository {
    override suspend fun saveWeather(weather: Weather) {
        val weatherEntity = weather.toWeatherEntity()
        dao.saveWeather(weatherEntity)
    }

    override suspend fun removeWeatherById(id: Int) {
        dao.removeWeatherById(id)
    }

    override suspend fun getWeathers(): List<Weather> {
        return dao.getWeathers().toWeatherEntity()
    }

    override suspend fun getWeatherById(id: Int): Weather {
        return dao.getWeatherById(id).toWeather()
    }
}