package com.example.superweather.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.superweather.ui.screens.components.LoadingComponent
import com.example.superweather.ui.screens.components.LocationPointerAnimation
import com.example.superweather.ui.screens.components.LottieAnimationIterations
import com.example.superweather.ui.screens.components.WeatherDetailsComponent
import com.example.superweather.ui.screens.components.WeatherInfoEmptyComponent
import com.example.superweather.ui.theme.RandomColor
import com.leumas.superweather.R

@Composable
fun HomeScreen(
    weatherState: WeatherState,
    onWeatherInfoEmptyButtonClick: () -> Unit
) {
    val localLottieIterations = compositionLocalOf { LottieAnimationIterations(LottieConstants.IterateForever) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(21, 153, 247, 255),
        content = {
            if (weatherState.isLoading) {
                LoadingComponent()
            } else {
                if (weatherState.isWeatherInfoEmpty) {
                    WeatherInfoEmptyComponent(
                        onClickButton = { onWeatherInfoEmptyButtonClick() }
                    )
                } else {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = CenterHorizontally
                    ) {
                        weatherState.date?.let {
                            Text(
                                modifier = Modifier.padding(top = 18.dp),
                                fontSize = 18.sp,
                                text = it,
                                color = Color(0xFF, 0xFF, 0xFF, 0x99)
                            )
                        }

                        Row {
                            Text(
                                modifier = Modifier.padding(top = 10.dp),
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                text = weatherState.weatherInfo.location,
                                color = RandomColor
                            )
                            if (weatherState.isCurrentLocation) {
                                LocationPointerAnimation(
                                    Modifier
                                        .size(50.dp)
                                        .defaultMinSize(minWidth = 25.dp)
                                        .padding(top = 4.dp)
                                )
                            }
                        }

                        weatherState.weatherRowViewEntity?.let {
                            val animatedComposition by rememberLottieComposition(it.icon)
                            LottieAnimation(
                                modifier = Modifier.size(200.dp),
                                composition = animatedComposition,
                                speed = 0.5f,
                                iterations = localLottieIterations.current.iterations
                            )
                        }
                        Text(
                            modifier = Modifier.padding(top = 10.dp),
                            fontSize = 58.sp,
                            fontWeight = FontWeight.Bold,
                            text = weatherState.weatherInfo.temperature,
                            color = Color(0xFF, 0xFF, 0xFF, 0xFF)
                        )

                        Row {
                            Text(
                                modifier = Modifier.padding(top = 10.dp),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                text = stringResource(id = R.string.acronym_high_temperature) + weatherState.weatherInfo.high,
                                color = Color(0xFF, 0xFF, 0xFF, 0xFF)
                            )
                            Text(
                                modifier = Modifier.padding(start = 6.dp, top = 10.dp),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                text = stringResource(id = R.string.acronym_low_temperature) + weatherState.weatherInfo.low,
                                color = Color(0xFF, 0xFF, 0xFF, 0xFF)
                            )
                        }
                        Text(
                            modifier = Modifier.padding(top = 6.dp),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            text = weatherState.weatherInfo.condition,
                            color = Color(0xFF, 0xFF, 0xFF, 0xFF)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 16.dp),
                            verticalAlignment = Alignment.Bottom,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            WeatherDetailsComponent(weatherState)
                        }
                    }
                }
            }
        }
    )
}

