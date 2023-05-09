package com.example.superweather.data.remote

import com.example.superweather.data.keys.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("2.5/weather?units=metric&appid=$API_KEY")
    suspend fun getWeatherData(
        @Query("q") name: String
    ) : WeatherDataDTO

    @GET("2.5/weather?units=metric&appid=$API_KEY")
    suspend fun getWeatherData(
        @Query("latitude") lat: String,
        @Query("longitude") long: String,
    ) : WeatherDataDTO
}
