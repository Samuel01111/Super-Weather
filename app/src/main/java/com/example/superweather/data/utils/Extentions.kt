package com.example.superweather.data.utils

fun String.toDegrees(): String {
    return "$this°C"
}

fun String.toDegreesWithoutPoint(): String {
    return "$this°"
}

fun String.toPercentage(): String {
    return "$this%"
}

fun String.toPressure(): String {
    return this + "hPa"
}

fun String.ToVelocity(): String {
    return this + "km/h"
}