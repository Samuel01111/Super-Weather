package com.example.superweather.data

import com.example.superweather.data.models.Weathers
import com.example.superweather.data.repository.WeatherAPIRepository
import timber.log.Timber
import javax.inject.Inject

class RefetchAfterOneHourUseCase @Inject constructor(
    private val weatherRepository: WeatherAPIRepository
) {
    suspend fun getWeathers(names: List<String>): Weathers {
        var weathersData = Weathers(emptyList())
            try {
                weathersData = weatherRepository.getWeathers(names).data
                Timber.i("RefetchAfterOneHourUseCase favorites: $weathersData")
            } catch (e: Exception) {
                Timber.e("Error getting favorites: $e")
            }
        return weathersData
    }
}
