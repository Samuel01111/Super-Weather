package com.example.superweather.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.superweather.data.db.WeatherEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveWeather(entity: WeatherEntity)

    @Query("select * from weather")
    fun getWeathers(): Flow<List<WeatherEntity>>

    @Query("select * from weather where id == :id")
    fun getWeatherById(id: Int): Flow<WeatherEntity>

    @Query("delete from weather where id == :id")
    suspend fun removeWeatherById(id: Int)
}
