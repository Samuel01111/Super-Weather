package com.example.superweather.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val location: String,
    val temperature: String,
    val condition: String,
    val high: String,
    val low: String,
    val humidity: String?,
    val pressure: String?,
    val speed: String?,
    val deg: String?
)
