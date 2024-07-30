package com.example.superweather.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather?units=metric&appid=da579bd6d6616e9a333a94a2a0589f2c")
    suspend fun getWeatherData(
        @Query("q") name: String
    ) : WeatherDataDTO

    @GET("weather?units=metric&appid=da579bd6d6616e9a333a94a2a0589f2c")
    suspend fun getWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") long: Double,
    ) : WeatherDataDTO
}
