package com.example.superweather.di

import com.example.superweather.data.repository.WeatherDatabaseDataBaseDataSource
import com.example.superweather.data.repository.WeatherDatabaseRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun provideLocalDataSource(weatherDataSourceModule: WeatherDatabaseDataBaseDataSource): WeatherDatabaseRepository
}