package com.example.superweather.data.utils

import com.example.superweather.data.models.Weather

fun getEmptyWeather(): Weather {
    return Weather(
        1,
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        ""
    )
}
