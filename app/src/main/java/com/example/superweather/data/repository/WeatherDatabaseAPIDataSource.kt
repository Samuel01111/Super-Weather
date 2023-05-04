package com.example.superweather.data.repository

import com.example.superweather.data.db.dao.WeatherDao
import com.example.superweather.data.models.Weather
import javax.inject.Inject

class WeatherDatabaseAPIDataSource @Inject constructor(
    private val dao: WeatherDao
): WeatherAPI{

    override suspend fun getWeatherByName(name: String): Weather {

    }

    override suspend fun getWeatherByLocalization(lat: Double, long: Double): Weather {

    }
}