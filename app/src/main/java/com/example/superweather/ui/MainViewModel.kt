package com.example.superweather.ui

import androidx.lifecycle.ViewModel
import com.example.superweather.ui.weather.WeatherData
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {

    fun getWeatherData(): WeatherData {
        return WeatherData(
            "New York",
            temperature = "7°C",
            condition = "Nubladão slk kkkkk",
            high = "H: 7°C",
            low = "L:-2°C"
        )
    }

}