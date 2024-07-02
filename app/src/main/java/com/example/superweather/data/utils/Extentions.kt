package com.example.superweather.data.utils

import android.content.Context
import androidx.core.content.ContextCompat
import com.leumas.superweather.R
import retrofit2.HttpException

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

fun String.toVelocity(): String {
    return this + "km/h"
}

fun Exception.toErrorMessage(context: Context): String {
    return when ((this as HttpException).code()) {
        404 -> {
            ContextCompat.getString(context, R.string.error_exception_not_found)
        }
        else -> {
            ContextCompat.getString(context, R.string.error_exception_generic)
        }
    }
}
