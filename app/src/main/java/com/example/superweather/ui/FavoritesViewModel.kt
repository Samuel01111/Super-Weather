package com.example.superweather.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.superweather.data.mapper.getWeatherRowViewEntity
import com.example.superweather.data.repository.WeatherDatabaseRepository
import com.example.superweather.ui.screens.FavoriteState
import com.example.superweather.ui.screens.WeatherRowViewEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    val repository: WeatherDatabaseRepository
): ViewModel() {

    var favoriteState by mutableStateOf(FavoriteState(emptyList(), false))
        private set

    var isOpenedBottomSheet by mutableStateOf(false)

    fun fetchFavorites(): List<WeatherRowViewEntity> {
        var weathersRows = emptyList<WeatherRowViewEntity>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                favoriteState = favoriteState.copy(
                    isLoading = true
                )
                weathersRows = async {
                    repository.getWeathers().map { weather ->
                        getWeatherRowViewEntity(weather)
                    }
                }.await()
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

    fun removeFavorite(list: List<WeatherRowViewEntity>): Boolean {
        var removed = 0
        CoroutineScope(Dispatchers.IO).launch {
            list.forEach { item ->
                try {
                    removed = async {
                        repository.removeWeatherById(item.id)
                    }.await()

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
}
