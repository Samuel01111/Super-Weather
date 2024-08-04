package com.example.superweather.di

import androidx.annotation.Keep
import com.example.superweather.data.repository.WeatherAPIRepository
import com.example.superweather.data.repository.WeatherAPIRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Keep
@Module
abstract class DataSourceModule {

    @Keep
    @Singleton
    @Binds
    abstract fun provideDataSource(weatherAPIRepositoryImpl: WeatherAPIRepositoryImpl): WeatherAPIRepository
}
