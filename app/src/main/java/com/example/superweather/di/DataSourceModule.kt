package com.example.superweather.di

import com.example.superweather.data.repository.WeatherDataBaseDataSource
import com.example.superweather.data.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun provideLocalDataSource(weatherDataSourceModule: WeatherDataBaseDataSource): WeatherRepository
}