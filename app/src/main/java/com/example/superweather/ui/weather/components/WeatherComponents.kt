package com.example.superweather.ui.weather.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants.IterateForever
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.superweather.ui.weather.LottieAnimationIterations
import com.example.superweather.ui.weather.WeatherRowViewEntity
import com.example.superweather.ui.weather.WeatherState

@Composable
fun WeatherRow(
    viewEntity: WeatherRowViewEntity?,
    onItemClicked: () -> Unit
) {
    viewEntity?.let {
        val animatedComposition by rememberLottieComposition(it.icon)
        val localLottieIterations = compositionLocalOf { LottieAnimationIterations(IterateForever) }

        Row(
            modifier = Modifier
                .background(viewEntity.backgroundColor, RoundedCornerShape(16))
                .fillMaxWidth()
                .padding(22.dp)
                .height(120.dp)
                .clickable {
                    onItemClicked()
                },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.8f),
                    text = it.location,
                    fontWeight = FontWeight.Normal,
                    color = Color.White,
                    fontSize = 22.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = it.temperature,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 55.sp
                )
            }

            LottieAnimation(
                modifier = Modifier
                    .size(100.dp)
                    .defaultMinSize(minWidth = 100.dp)
                    .padding(horizontal = 16.dp),
                composition =  animatedComposition,
                iterations = localLottieIterations.current.iterations,
                contentScale = ContentScale.Crop,
                outlineMasksAndMattes = true
            )
        }
    }
}

@Composable
fun WeatherDetailsComponent(
    weather: WeatherState
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(140.dp)
        .horizontalScroll(rememberScrollState())
        .background(
            color = Color(0, 0, 0, 70),
            shape = RoundedCornerShape(16)
        ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        weather.weatherItems?.forEach {
            WeatherDetailsColumnComponent(it.title, it.value, it.icon)
        }
    }
}

@Composable
fun WeatherDetailsColumnComponent(title: String, value: String, icon: LottieCompositionSpec.RawRes) {
    Column(modifier = Modifier
        .fillMaxHeight()
        .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        val animatedComposition by rememberLottieComposition(icon)
        val localLottieIterations = compositionLocalOf { LottieAnimationIterations(IterateForever) }

        LottieAnimation(
            modifier = Modifier
                .size(52.dp)
                .align(Alignment.CenterHorizontally),
            composition =  animatedComposition,
            iterations = localLottieIterations.current.iterations,
            contentScale = ContentScale.Crop
        )

        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = value,
            fontWeight = FontWeight.Normal,
            color = Color.White,
            fontSize = 14.sp
        )

        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = title,
            fontWeight = FontWeight.Normal,
            color = Color(255, 255, 255, 90),
            fontSize = 12.sp
        )
    }
}
