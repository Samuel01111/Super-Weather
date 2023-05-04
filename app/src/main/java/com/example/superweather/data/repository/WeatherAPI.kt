package com.example.superweather.data.repository

import com.example.superweather.data.models.Weather
import retrofit2.http.GET

interface WeatherAPI {

    suspend fun getWeatherByName(name: String): Weather

    @GET("")
    suspend fun getWeatherByLocalization(lat: Double, long: Double): Weather
}
