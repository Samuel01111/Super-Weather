package com.example.superweather.ui.weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.superweather.R
import com.example.superweather.ui.weather.components.WeatherDetailsComponent

@Composable
fun HomeScreen(
    weatherState: WeatherState,
    backgroundImage: Int,
    resId: Int
) {
    val painter = painterResource(id = backgroundImage)
    val stateValue by remember(weatherState) { mutableStateOf(weatherState) }
    val localLottieIterations = compositionLocalOf { LottieAnimationIterations(LottieConstants.IterateForever) }
    var stateLocationValue by remember(weatherState.weatherInfo.location) { mutableStateOf(weatherState.weatherInfo.location) }
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

                    Text(
                        modifier = Modifier.padding(top = 10.dp),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        text = weatherState.weatherInfo.location,
                        color = Color(0xFF, 0xFF, 0xFF, 0xFF)
                    )

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

                    Text(
                        modifier = Modifier.padding(top = 6.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        text = weatherState.weatherInfo.condition,
                        color = Color(0xFF, 0xFF, 0xFF, 0xFF)
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        WeatherDetailsComponent(weatherState)
                    }
                }
            }
        }
    )
}
