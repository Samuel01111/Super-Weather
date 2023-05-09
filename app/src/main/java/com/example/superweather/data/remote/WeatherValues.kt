package com.example.superweather.data.remote

import com.squareup.moshi.Json

data class WeatherValues(
    @field:Json(name = "temp") val temperature: Double,
    @field:Json(name = "feels_like") val feelsLike: Double,
    @field:Json(name = "temp_min") val minTemp: Double,
    @field:Json(name = "temp_max") val maxTemp: Double,
    val pressure: Int?,
    val huminity: Int?
    )

/* {
    "coord": {
        "lon": -46.6361,
        "lat": -23.5475
    },
    "weather": [
        {
            "id": 803,
            "main": "Clouds",
            "description": "broken clouds",
            "icon": "04n"
        }
    ],
    "base": "stations",
    "main": {
        "temp": 20.73,
        "feels_like": 21.16,
        "temp_min": 20.2,
        "temp_max": 21.92,
        "pressure": 1020,
        "humidity": 88
    },
    "visibility": 7000,
    "wind": {
        "speed": 2.06,
        "deg": 140
    },
    "clouds": {
        "all": 75
    },
    "dt": 1683162554,
    "sys": {
        "type": 2,
        "id": 2082654,
        "country": "BR",
        "sunrise": 1683106055,
        "sunset": 1683146350
    },
    "timezone": -10800,
    "id": 3448439,
    "name": "São Paulo",
    "cod": 200
}*/