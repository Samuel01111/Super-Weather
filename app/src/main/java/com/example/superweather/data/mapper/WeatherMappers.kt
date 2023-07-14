package com.example.superweather.data.mapper

import com.example.superweather.data.db.WeatherEntity
import com.example.superweather.data.models.Weather
import com.example.superweather.data.remote.WeatherDataDTO
import com.example.superweather.data.utils.ToVelocity
import com.example.superweather.data.utils.toDegrees
import com.example.superweather.data.utils.toDegreesWithoutPoint
import com.example.superweather.data.utils.toPercentage
import com.example.superweather.data.utils.toPressure

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

fun Array<WeatherEntity>.toWeatherEntity(): List<Weather> {
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
            speed = this.wind.speed.toString().ToVelocity(),
            deg = this.wind.deg.toString()
        )
    }
}
