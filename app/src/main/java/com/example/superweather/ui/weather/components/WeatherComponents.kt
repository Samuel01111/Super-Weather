package com.example.superweather.ui.weather.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
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

@Composable
fun WeatherRow(
    weatherRowViewEntity: WeatherRowViewEntity?,
) {
    weatherRowViewEntity?.let {
        val animatedComposition by rememberLottieComposition(it.icon)
        val localLottieIterations = compositionLocalOf { LottieAnimationIterations(IterateForever) }
        Row(
            modifier = Modifier
                .background(weatherRowViewEntity.backgroundColor, RoundedCornerShape(16))
                .fillMaxWidth()
                .padding(22.dp)
                .height(120.dp),
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
                    .padding(horizontal = 16.dp)
                ,composition =  animatedComposition,
                iterations = localLottieIterations.current.iterations,
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun WeatherDetailsComponent(
    //weather: WeatherDetailsViewEntity
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(32.dp)
        .height(72.dp)
        .background(
            color = Color(0, 0, 0, 60),
            shape = RoundedCornerShape(16)
        ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        //WeatherDetailsColumnComponent(weather.title, weather.value, weather.icon)
    }
}

@Composable
fun WeatherDetailsColumnComponent(title: String, value: String, icon: LottieCompositionSpec.RawRes) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(52.dp)
    ) {
        val animatedComposition by rememberLottieComposition(icon)
        val localLottieIterations = compositionLocalOf { LottieAnimationIterations(IterateForever) }

        LottieAnimation(
            modifier = Modifier.size(24.dp),
            composition =  animatedComposition,
            iterations = localLottieIterations.current.iterations,
            contentScale = ContentScale.Crop
        )

        Text(
            text = value,
            fontWeight = FontWeight.Normal,
            color = Color.White,
            fontSize = 14.sp
        )

        Text(
            text = title,
            fontWeight = FontWeight.Normal,
            color = Color(255, 255, 255, 90),
            fontSize = 12.sp
        )
    }
}

//@Composable
//fun HomeScreen(
//
//) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(colorResource(id = R.color.teal_700))
//            .wrapContentSize(Alignment.Center)
//    ) {
//        Text(
//            text = "Home Screen",
//            fontWeight = FontWeight.Bold,
//            color = Color.White,
//            modifier = Modifier.align(Alignment.CenterHorizontally),
//            textAlign = TextAlign.Center,
//            fontSize = 20.sp
//        )
//    }
//}

/*Button(modifier = Modifier
                    .border(BorderStroke(1.dp, Color.Red), shape = RoundedCornerShape(100))
                    .size(40.dp),
                    onClick = { /*Open Screen*/ }
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        shape = RoundedCornerShape(100),
                        color = Color(107, 24, 24, 60)
                    ) {

                    }
                }*/