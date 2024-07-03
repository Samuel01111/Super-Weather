package com.example.superweather.data.remote

import com.leumas.superweather.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather?units=metric&appid=${BuildConfig.WEATHER_API_KEY}")
    suspend fun getWeatherData(
        @Query("q") name: String
    ) : WeatherDataDTO

    @GET("weather?units=metric&appid=${BuildConfig.WEATHER_API_KEY}")
    suspend fun getWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") long: Double,
    ) : WeatherDataDTO
}
