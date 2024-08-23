package com.example.superweather.data.utils

import com.example.superweather.data.models.Weather
import java.time.Instant
import java.util.Date

fun getEmptyWeather(): Weather {
    return Weather(
        1,
        Date.from(Instant.now()),
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
