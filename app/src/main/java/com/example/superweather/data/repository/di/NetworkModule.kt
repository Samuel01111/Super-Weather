package com.example.superweather.data.repository.di

import android.content.Context
import com.example.superweather.data.interceptor.FirebaseLoggingInterceptor
import com.example.superweather.data.remote.WeatherApi
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule(

) {

    @Singleton
    @Provides
    fun providesRetrofitBuilder(context: Context): WeatherApi {
        val firebaseAnalytics = FirebaseAnalytics.getInstance(context)

        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        FirebaseLoggingInterceptor(firebaseAnalytics)
                    )
                    .addInterceptor(
                        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                    ).build()
            )
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
}
