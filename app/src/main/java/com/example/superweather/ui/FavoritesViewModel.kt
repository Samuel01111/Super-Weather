package com.example.superweather.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.superweather.data.RefetchAfterOneHourUseCase
import com.example.superweather.data.mapper.getWeatherRowViewEntity
import com.example.superweather.data.models.Weather
import com.example.superweather.data.repository.WeatherDatabaseRepository
import com.example.superweather.data.utils.getEmptyWeather
import com.example.superweather.ui.screens.FavoriteState
import com.example.superweather.ui.screens.WeatherRowViewEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    val repository: WeatherDatabaseRepository,
    private val refetchAfterOneHourUseCase: RefetchAfterOneHourUseCase
): ViewModel() {

    var favoriteState by mutableStateOf(FavoriteState(emptyList(), false))
        private set

    var weathers = mutableListOf<Weather>()

    fun fetchFavorites(): List<WeatherRowViewEntity> {
        var weathersRows = emptyList<WeatherRowViewEntity>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                favoriteState = favoriteState.copy(
                    isLoading = true
                )
                weathersRows = repository.getWeathers().map { weather ->
                        weathers.add(weather)
                        getWeatherRowViewEntity(weather)
                    }

                if (isMoreThanOneHour(weathersRows.first())) {
                    val weathers = refetchAfterOneHourUseCase.getWeathers(
                            weathersRows.map {
                                it.location
                            }).weathers

                    weathers.forEach {
                        async {
                            repository.saveWeather(it)
                        }
                    }
                    weathersRows = weathers.map { getWeatherRowViewEntity(it) }
                }

                favoriteState = favoriteState.copy(
                    weatherRows = weathersRows,
                    isLoading = false
                )
                Timber.d("Favorites $weathersRows")
            } catch (e: Exception) {
                favoriteState = favoriteState.copy(
                    weatherRows = emptyList(),
                    isLoading = false
                )
                Timber.e("Error getting favorites: $e")
            }
        }
        return weathersRows
    }

    fun fetchFavorite(weatherRow: WeatherRowViewEntity): Weather {
            var weather: Weather? = null
            for (item in weathers) {
                if (item.location == weatherRow.location) {
                    weather = item
                    break
                }
            }
        return weather ?: getEmptyWeather()
    }

    fun removeFavorite(list: List<WeatherRowViewEntity>): Boolean {
        var removed = 0
        CoroutineScope(Dispatchers.Unconfined).launch {
            list.forEach { item ->
                try {
                    removed = async {
                        repository.removeWeatherById(item.id)
                    }.await()
                    Timber.i("removed item favorites: $item")
                } catch (e: Exception) {
                    favoriteState = favoriteState.copy(
                        weatherRows = emptyList(),
                        isLoading = false
                    )
                    Timber.e("Error getting favorites: $e")
                }
            }
        }
        return removed >= 1
    }

    private fun isMoreThanOneHour(weather: WeatherRowViewEntity): Boolean {
        val weatherDate = weather.date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
        val currentTime = LocalDateTime.now(ZoneId.systemDefault())
        val hoursDifference = ChronoUnit.HOURS.between(weatherDate, currentTime)

        return hoursDifference >= 1
    }
}
