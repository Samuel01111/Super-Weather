package com.example.superweather.data.repository

import android.content.Context
import com.example.superweather.data.mapper.toWeather
import com.example.superweather.data.models.Weather
import com.example.superweather.data.remote.WeatherApi
import com.example.superweather.data.utils.Resource
import com.example.superweather.data.utils.getEmptyWeather
import com.leumas.superweather.R
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class WeatherAPIRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
    val context: Context
): WeatherAPIRepository {
    override suspend fun getWeatherByName(name: String): Resource<Weather> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    name = name
                ).toWeather()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            handleError(e)
        }
    }

    override suspend fun getWeatherByLocalization(lat: Double, long: Double): Resource<Weather> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    lat = lat,
                    long = long
                ).toWeather()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            handleError(e)
        }
    }
    private fun handleError(e: Exception): Resource<Weather> {
        return when (e) {
            is SocketTimeoutException -> {
                Resource.Error(
                    message = context.getString(R.string.error_exception_check_your_internet),
                    data = getEmptyWeather()
                )
            }

            is UnknownHostException -> {
                return Resource.Dialog(
                    message = context.getString(R.string.error_exception_generic),
                    data = getEmptyWeather()
                )
            }

            is HttpException -> {
                return if (e.code() in 400..404) {
                    Resource.Error(
                        message = context.getString(R.string.error_exception_not_found),
                        data = getEmptyWeather()
                    )
                } else {
                     Resource.Dialog(
                        message = context.getString(R.string.error_exception_generic),
                        data = getEmptyWeather()
                    )
                }
            }

            else -> {
                return Resource.Dialog(
                    message = context.getString(R.string.error_exception_generic),
                    data = getEmptyWeather()
                )
            }
        }
    }
}
