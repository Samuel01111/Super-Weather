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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

import com.example.superweather.ui.screens.components.LocationPointerAnimation
import com.example.superweather.ui.screens.components.LottieAnimationIterations
import com.example.superweather.ui.screens.components.WeatherDetailsComponent
import com.leumas.superweather.R

@Composable
fun HomeScreen(
    weatherState: WeatherState
) {
    val localLottieIterations = compositionLocalOf { LottieAnimationIterations(LottieConstants.IterateForever) }
    val loadingAnimationComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.ic_lottie_weather_loading))

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(21, 153, 247, 255),
        content = {
            if (weatherState.isLoading) {
                Column(
                    modifier = Modifier
                        .size(500.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LottieAnimation(
                        composition = loadingAnimationComposition,
                        speed = 0.5f,
                        modifier = Modifier
                            .size(350.dp),
                        iterations = localLottieIterations.current.iterations
                    )
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
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
                            color = Color(0xFF, 0xFF, 0xFF, 0xFF)
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
                            text = "H: " + weatherState.weatherInfo.high,
                            color = Color(0xFF, 0xFF, 0xFF, 0xFF)
                        )
                        Text(
                            modifier = Modifier.padding(start = 6.dp, top = 10.dp),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            text = "L: " + weatherState.weatherInfo.low,
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

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 80.dp, horizontal = 16.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center
                ) {
                    WeatherDetailsComponent(weatherState)
                }
            }
        }
    )
}
