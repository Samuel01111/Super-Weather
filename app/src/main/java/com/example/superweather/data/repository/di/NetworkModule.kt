package com.example.superweather.data.repository.di

import com.example.superweather.data.keys.API_KEY
import com.example.superweather.data.repository.WeatherAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class NetworkModule {

    @Provides
    fun providesRetrofitBuilder(): WeatherAPI {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/weather&appid=$API_KEY")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                    ).build()
            )
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(WeatherAPI::class.java)
    }

}