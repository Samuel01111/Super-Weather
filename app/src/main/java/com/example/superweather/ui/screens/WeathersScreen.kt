package com.example.superweather.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.superweather.ui.screens.components.LottieAnimationIterations
import com.example.superweather.ui.screens.components.VersionAppComponent
import com.leumas.superweather.BuildConfig
import com.leumas.superweather.R

@Composable
fun WeathersScreen() {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(21, 153, 247, 255))
                .wrapContentSize(Alignment.Center)
        ) {
            val animatedComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.ic_lottie_weather_working_progress))
            val localLottieIterations = compositionLocalOf { LottieAnimationIterations(LottieConstants.IterateForever) }

            Text(
                text = stringResource(id = R.string.weather_screen_title),
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )

            Text(
                text = stringResource(id = R.string.weather_screen_subtitle),
                fontWeight = FontWeight.Bold,
                color = Color.Yellow,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                fontSize = 26.sp
            )

            LottieAnimation(
                modifier = Modifier
                    .size(200.dp)
                    .padding(top = 4.dp)
                    .align(Alignment.CenterHorizontally),
                composition =  animatedComposition,
                iterations = localLottieIterations.current.iterations,
                contentScale = ContentScale.Crop,
                outlineMasksAndMattes = true
            )
            VersionAppComponent(
                BuildConfig.VERSION_CODE.toString(),
                BuildConfig.VERSION_NAME
            )
        }
    }
}

@Preview
@Composable
fun WeathersScreenPreview() {
    WeathersScreen()
}