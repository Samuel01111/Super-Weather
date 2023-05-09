package com.example.superweather.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.superweather.data.db.WeatherEntity

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveWeather(entity: WeatherEntity)

    @Query("select * from weather_table")
    suspend fun getWeathers(): Array<WeatherEntity>

    @Query("select * from weather_table where id == :id")
    suspend fun getWeatherById(id: Int): WeatherEntity

    @Query("delete from weather_table where id == :id")
    suspend fun removeWeatherById(id: Int)
}
