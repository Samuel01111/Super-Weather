package com.example.superweather.di

import com.example.superweather.data.repository.WeatherAPIRepository
import com.example.superweather.data.repository.WeatherAPIRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun provideDataSource(weatherAPIRepositoryImpl: WeatherAPIRepositoryImpl): WeatherAPIRepository
}
