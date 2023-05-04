package com.example.superweather

import androidx.lifecycle.ViewModel
import com.example.superweather.data.repository.WeatherDatabaseRepository
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    private val repository: WeatherDatabaseRepository
) : ViewModel(){

    fun getWeatherByCity() {

    }
}
