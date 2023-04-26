package com.example.superweather.ui.weather

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

sealed class WeatherState {

    class Submit(
        textLocation: String?,
        textLocationError: Int? = null,
        weatherData: WeatherData? = null,
        weatherDataError: Int? = null
    ) : WeatherState() {
        var textLocation by mutableStateOf(textLocation)
        var textLocationError by mutableStateOf(textLocationError)
        var weatherData by mutableStateOf(weatherData)
        var weatherDataError by mutableStateOf(weatherDataError)
    }

    object Loading : WeatherState()
    object Error : WeatherState()
}

data class WeatherData(
    val location: String,
    val temperature: String,
    val condition: String,
    val high: String,
    val low: String
)

/*
    * class Submit(
            userName: String?,
            userNameError: Int? = null,
            email: String?,
            emailError: Int? = null,
            phone: String?,
            phoneError: Int? = null
    ) : SurveyState() {
        var userName by mutableStateOf(userName)
        var userNameError by mutableStateOf(userNameError, policy = neverEqualPolicy())
        var email by mutableStateOf(email)
        var emailError by mutableStateOf(emailError)
        var phone by mutableStateOf(phone)
        var phoneError by mutableStateOf(phoneError)
        var loading by mutableStateOf(false)
    } */