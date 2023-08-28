package com.example.superweather.ui.screens.components

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.superweather.R

@Composable
fun LocationPointerAnimation(modifier: Modifier) {
    val animatedComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.ic_lottie_weather_location_pointer))
    val localLottieIterations = compositionLocalOf { LottieAnimationIterations(LottieConstants.IterateForever) }

    LottieAnimation(
        modifier = Modifier
            .size(50.dp)
            .defaultMinSize(minWidth = 25.dp)
            .padding(top = 4.dp)
            .then(modifier),
        composition =  animatedComposition,
        iterations = localLottieIterations.current.iterations,
        contentScale = ContentScale.Crop,
        outlineMasksAndMattes = true
    )
}

@JvmInline
value class LottieAnimationIterations(val iterations: Int)
