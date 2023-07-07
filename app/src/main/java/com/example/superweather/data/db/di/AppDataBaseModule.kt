package com.example.superweather.data.db.di

import android.content.Context
import com.example.superweather.data.db.AppDatabase
import com.example.superweather.data.db.dao.WeatherDao
import dagger.Module
import dagger.Provides

@Module
class AppDataBaseModule {

    @Provides
    fun providesWeatherDao(
        context: Context
    ): WeatherDao {
        val database = AppDatabase.getDatabase(context)
        return database.weatherDao()
    }
}