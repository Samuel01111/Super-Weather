package com.example.superweather.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.example.superweather.R
import com.example.superweather.data.models.Weather
import com.example.superweather.data.repository.WeatherAPIRepository
import com.example.superweather.data.utils.Resource
import com.example.superweather.data.utils.getEmptyWeather
import com.example.superweather.domain.location.LocationTracker
import com.example.superweather.ui.weather.WeatherDetailsViewEntity
import com.example.superweather.ui.weather.WeatherRowViewEntity
import com.example.superweather.ui.weather.WeatherState
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

class MainViewModel @Inject constructor(
    val repository: WeatherAPIRepository,
    private val locationTracker: LocationTracker
) : ViewModel() {

    var state by mutableStateOf(WeatherState(getEmptyWeather()))
        private set

    private fun getLocalDateTime(): String {
        return LocalDateTime.now().month.toString() +
                " " +
                LocalDateTime.now().dayOfMonth.toString() +
                ", " +
                LocalDateTime.now().year.toString()
    }

    fun fetchWeatherByName(cityName: String) {
        viewModelScope.launch {
            when(val resource = repository.getWeatherByName(cityName.trim())) {
                is Resource.Success -> {
                    state = state.copy(
                        weatherInfo = resource.data,
                        isLoading = false,
                        error = null,
                        date = getLocalDateTime(),
                        weatherRowViewEntity = getWeatherRowViewEntity(resource.data)
                    )
                    Log.d("@@@", state.toString())
                }
                is Resource.Error -> {
                    state = state.copy(
                        weatherInfo = getEmptyWeather(),
                        isLoading = false,
                        error = resource.message
                    )
                }
            }
        }
    }

    fun fetchWeatherByLocalization() {
        viewModelScope.launch {
            locationTracker.getCurrentLocation()?.let {
                when(val resource = repository.getWeatherByLocalization(it.latitude, it.longitude)) {
                    is Resource.Success -> {
                        state = state.copy(
                            weatherInfo = resource.data,
                            isLoading = false,
                            error = null,
                            date = getLocalDateTime(),
                            weatherRowViewEntity = getWeatherRowViewEntity(resource.data),
                            weatherItems = getWeatherDetailsItems(resource.data)
                        )
                        Log.d("@@@", state.toString())
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            weatherInfo = getEmptyWeather(),
                            isLoading = false,
                            error = resource.message
                        )
                    }
                }
            } ?: kotlin.run {
                state = state.copy(
                    isLoading = false,
                    error = "Couldn't retrieve location. Make sure to grant permission and enable GPS."
                )
            }
        }
    }

    private fun getWeatherRowViewEntity(weatherInfo: Weather): WeatherRowViewEntity {
        return WeatherRowViewEntity(
            location = weatherInfo.location,
            temperature = weatherInfo.temperature,
            icon = getIconByCondition(weatherInfo.condition),
            backgroundColor = getBackgroundColorByCondition(weatherInfo.condition)
        )
    }

    private fun getWeatherDetailsItems(weatherInfo: Weather): List<WeatherDetailsViewEntity> {

        return listOf(
            WeatherDetailsViewEntity(
                title = "Huminity",
                value = weatherInfo.humidity ?: "",
                icon = LottieCompositionSpec.RawRes(R.raw.ic_lottie_weather_humidity),
            ),
            WeatherDetailsViewEntity(
                title = "Pressure",
                value = weatherInfo.pressure ?: "",
                icon = LottieCompositionSpec.RawRes(R.raw.ic_lottie_weather_pressure),
            ),
            WeatherDetailsViewEntity(
                title = "Wind",
                value = weatherInfo.speed ?: "",
                icon = LottieCompositionSpec.RawRes(R.raw.ic_lottie_weather_speed),
            )
        )
    }

    private fun getBackgroundColorByCondition(condition: String): Color {
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

    private fun getIconByCondition(condition: String): LottieCompositionSpec.RawRes {
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
}
