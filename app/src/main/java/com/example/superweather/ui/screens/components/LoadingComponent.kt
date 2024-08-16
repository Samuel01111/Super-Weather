package com.example.superweather.ui.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.leumas.superweather.R

@Composable
fun LoadingComponent(modifier: Modifier = Modifier) {
    val localLottieIterations = compositionLocalOf { LottieAnimationIterations(LottieConstants.IterateForever) }
    val loadingAnimationComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.ic_lottie_weather_loading_flowers))

    Column(
        modifier = modifier
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
}