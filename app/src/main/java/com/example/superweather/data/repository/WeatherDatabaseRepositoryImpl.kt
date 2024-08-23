package com.example.superweather.data.repository

import com.example.superweather.data.db.dao.WeatherDao
import com.example.superweather.data.mapper.toWeather
import com.example.superweather.data.mapper.toWeatherEntity
import com.example.superweather.data.models.Weather
import com.example.superweather.data.utils.getEmptyWeather
import kotlinx.coroutines.flow.first
import timber.log.Timber
import javax.inject.Inject

class WeatherDatabaseRepositoryImpl @Inject constructor(
    private val dao: WeatherDao
): WeatherDatabaseRepository {
    override suspend fun saveWeather(weather: Weather) {
        val weatherEntity = weather.toWeatherEntity()
        dao.saveWeather(weatherEntity)
        Timber.i("Save Weather Entity $weatherEntity")
    }

    override suspend fun removeWeatherById(id: Long): Int {
        return dao.removeWeatherById(id)
    }

    override suspend fun getWeathers(): List<Weather> {
        val weathers = dao.getWeathers().first().toWeatherEntity()
        return weathers
    }

    override suspend fun getWeatherById(id: Long): Weather {
        var weather: Weather = getEmptyWeather()
        dao.getWeatherById(id).collect { weatherData ->
            weather = weatherData.toWeather()
        }
        return weather
    }
}