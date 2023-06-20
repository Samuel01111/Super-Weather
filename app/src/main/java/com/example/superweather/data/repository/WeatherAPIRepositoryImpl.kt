package com.example.superweather.data.repository

import com.example.superweather.data.mapper.toWeather
import com.example.superweather.data.models.Weather
import com.example.superweather.data.remote.WeatherApi
import com.example.superweather.data.utils.Resource
import com.example.superweather.data.utils.getEmptyWeather
import javax.inject.Inject

class WeatherAPIRepositoryImpl @Inject constructor(
    private val api: WeatherApi
): WeatherAPIRepository {

    override suspend fun getWeatherByName(name: String): Resource<Weather> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    name = name
                ).toWeather()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(
                message = e.message ?: "An unknown error occurred.",
                data = getEmptyWeather()
            )
        }
    }

    override suspend fun getWeatherByLocalization(lat: Double, long: Double): Resource<Weather> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    lat = lat,
                    long = long
                ).toWeather()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(
                message = e.message ?: "An unknown error occurred.",
                data = getEmptyWeather()
            )
        }
    }
}