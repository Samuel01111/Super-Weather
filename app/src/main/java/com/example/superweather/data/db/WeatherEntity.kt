package com.example.superweather.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.superweather.data.models.Weather

@Entity(tableName = "weather_table")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val location: String,
    val temperature: String,
    val condition: String,
    val high: String,
    val low: String
)

fun Weather.toWeatherEntity(id: Int = 0): WeatherEntity {
    return with(this) {
        WeatherEntity(
            id = id.toLong(),
            title = title,
            location = location,
            temperature = temperature,
            condition = condition,
            high = high,
            low = low
        )
    }
}

fun WeatherEntity.toWeather(id: Int = 0): Weather {
    return with(this) {
        Weather(
            id = id.toLong(),
            title = title,
            location = location,
            temperature = temperature,
            condition = condition,
            high = high,
            low = low
        )
    }
}

fun Array<WeatherEntity>.toWeatherEntity(): List<Weather> {
    return this.map {
        Weather(
            id = it.id,
            title = it.title,
            location = it.location,
            temperature = it.temperature,
            condition = it.condition,
            high = it.high,
            low = it.low
        )
    }
}
