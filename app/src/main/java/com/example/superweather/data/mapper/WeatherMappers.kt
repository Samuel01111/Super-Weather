package com.example.superweather.data.mapper

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.example.superweather.data.db.WeatherEntity
import com.example.superweather.data.models.Weather
import com.example.superweather.data.remote.WeatherDataDTO
import com.example.superweather.data.utils.toDegrees
import com.example.superweather.data.utils.toDegreesWithoutPoint
import com.example.superweather.data.utils.toPercentage
import com.example.superweather.data.utils.toPressure
import com.example.superweather.data.utils.toVelocity
import com.example.superweather.ui.screens.WeatherDetailsViewEntity
import com.example.superweather.ui.screens.WeatherRowViewEntity
import com.leumas.superweather.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun Weather.toWeatherEntity(id: Int = 0): WeatherEntity {
    return with(this) {
        WeatherEntity(
            id = id.toLong(),
            location = location,
            temperature = temperature,
            condition = condition,
            high = high,
            low = low,
            humidity = humidity,
            pressure = pressure,
            speed = speed,
            deg = deg
        )
    }
}

fun WeatherEntity.toWeather(id: Int = 0): Weather {
    return with(this) {
        Weather(
            id = id.toLong(),
            location = location,
            temperature = temperature,
            condition = condition,
            high = high,
            low = low,
            humidity = humidity,
            pressure = pressure,
            speed = speed,
            deg = deg
        )
    }
}

fun List<WeatherEntity>.toWeatherEntity(): List<Weather> {
    return this.map {
        Weather(
            id = it.id,
            location = it.location,
            temperature = it.temperature,
            condition = it.condition,
            high = it.high,
            low = it.low,
            humidity = it.humidity,
            pressure = it.pressure,
            speed = it.speed,
            deg = it.deg
        )
    }
}

fun WeatherDataDTO.toWeather() : Weather {
    return with(this) {
        Weather(
            id = id,
            location = name,
            temperature = this.weatherValues.temperature.toInt().toString().toDegrees(),
            condition = this.weatherCondition[0].condition,
            high = this.weatherValues.maxTemp.toInt().toString().toDegreesWithoutPoint(),
            low = this.weatherValues.minTemp.toInt().toString().toDegreesWithoutPoint(),
            humidity = this.weatherValues.humidity.toString().toPercentage(),
            pressure = this.weatherValues.pressure.toString().toPressure(),
            speed = this.wind.speed.toString().toVelocity(),
            deg = this.wind.deg.toString()
        )
    }
}

fun getWeatherRowViewEntity(weatherInfo: Weather): WeatherRowViewEntity {
    return WeatherRowViewEntity(
        location = weatherInfo.location,
        temperature = weatherInfo.temperature,
        icon = getIconByCondition(weatherInfo.condition),
        backgroundColor = getBackgroundColorByCondition(weatherInfo.condition)
    )
}

fun getWeatherDetailsItems(weatherInfo: Weather, context: Context): List<WeatherDetailsViewEntity> {
    return listOf(
        WeatherDetailsViewEntity(
            title = ContextCompat.getString(context, R.string.weather_details_condition_humidity),
            value = weatherInfo.humidity ?: "",
            icon = LottieCompositionSpec.RawRes(R.raw.ic_lottie_weather_humidity),
        ),
        WeatherDetailsViewEntity(
            title = ContextCompat.getString(context, R.string.weather_details_condition_pressure),
            value = weatherInfo.pressure ?: "",
            icon = LottieCompositionSpec.RawRes(R.raw.ic_lottie_weather_pressure),
        ),
        WeatherDetailsViewEntity(
            title = ContextCompat.getString(context, R.string.weather_details_condition_wind),
            value = weatherInfo.speed ?: "",
            icon = LottieCompositionSpec.RawRes(R.raw.ic_lottie_weather_speed),
        )
    )
}

fun getBackgroundColorByCondition(condition: String): Color {
    return when(condition) {
        "Thunderstorm" -> Color(78, 107, 153, 255)
        "Drizzle" -> Color(148, 165, 186, 255)
        "Rain" -> Color(113, 126, 141, 255)
        "Snow" -> Color(190, 205, 228, 255)
        "Clear" -> Color(254, 180, 115, 255)
        "Clouds" -> Color(148, 165, 186, 255)
        else -> { Color(69, 172, 248, 255) }
    }
}

fun getIconByCondition(condition: String): LottieCompositionSpec.RawRes {
    return when(condition) {
        "Thunderstorm" -> LottieCompositionSpec.RawRes(R.raw.ic_lottie_weather_thunderstorm)
        "Drizzle" -> LottieCompositionSpec.RawRes(R.raw.ic_lottie_weather_drizzle_shower)
        "Rain" -> LottieCompositionSpec.RawRes(R.raw.ic_lottie_weather_rain)
        "Snow" -> LottieCompositionSpec.RawRes(R.raw.ic_lottie_weather_snow)
        "Clear" -> LottieCompositionSpec.RawRes(R.raw.ic_lottie_weather_clear)
        "Clouds" -> LottieCompositionSpec.RawRes(R.raw.ic_lottie_weather_clouds)
        else -> { LottieCompositionSpec.RawRes(R.raw.ic_lottie_weather_clear) }
    }
}

fun getLocalDateTime(): String {
    val formatter = DateTimeFormatter.ofPattern("KK:mm a", Locale.getDefault())
    val hours = LocalDateTime.now().format(formatter)
    return hours +
            " " +
            LocalDateTime.now().month.toString() +
            " " +
            LocalDateTime.now().dayOfMonth.toString() +
            ", " +
            LocalDateTime.now().year.toString()
}
