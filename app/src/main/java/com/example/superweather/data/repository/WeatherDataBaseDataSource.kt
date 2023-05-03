package com.example.superweather.data.repository

import com.example.superweather.data.db.WeatherEntity
import com.example.superweather.data.db.dao.WeatherDao
import com.example.superweather.data.db.toWeather
import com.example.superweather.data.db.toWeatherEntity
import com.example.superweather.data.models.Weather
import javax.inject.Inject

class WeatherDataBaseDataSource @Inject constructor(
    private val dao: WeatherDao
): WeatherRepository {
    override suspend fun saveWeather(weather: Weather) {
        val weatherEntity = weather.toWeatherEntity()
        dao.saveWeather(weatherEntity)
    }

    override suspend fun updateWeather(entity: WeatherEntity) {
        dao.saveWeather(entity)
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