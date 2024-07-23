package com.example.superweather.data.repository

import com.example.superweather.data.db.dao.WeatherDao
import com.example.superweather.data.mapper.toWeather
import com.example.superweather.data.mapper.toWeatherEntity
import com.example.superweather.data.models.Weather
import com.example.superweather.data.utils.getEmptyWeather
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
        var weathers: List<Weather> = emptyList()
        dao.getWeathers().collect { weatherData ->
            weathers = weatherData.toWeatherEntity()
        }
        return weathers
    }

    override suspend fun getWeatherById(id: Int): Weather {
        var weather: Weather = getEmptyWeather()
        dao.getWeatherById(id).collect { weatherData ->
            weather = weatherData.toWeather()
        }
        return weather
    }
}