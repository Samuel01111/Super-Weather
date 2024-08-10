package com.example.superweather.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superweather.data.mapper.getWeatherRowViewEntity
import com.example.superweather.data.repository.WeatherDatabaseRepository
import com.example.superweather.ui.screens.FavoriteState
import com.example.superweather.ui.screens.WeatherRowViewEntity
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    val repository: WeatherDatabaseRepository
): ViewModel() {

    var favoriteState by mutableStateOf(FavoriteState(emptyList()))
        private set

    fun fetchFavorites(): List<WeatherRowViewEntity> {
        var weathersRow = emptyList<WeatherRowViewEntity>()
        viewModelScope.launch {
            weathersRow = repository.getWeathers().map { weather ->
                getWeatherRowViewEntity(weather)
            }
        }
        return weathersRow
    }
}
