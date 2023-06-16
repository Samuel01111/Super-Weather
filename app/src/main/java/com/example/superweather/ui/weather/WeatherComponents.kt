package com.example.superweather.ui.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants.IterateForever
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.superweather.R
import com.example.superweather.data.models.Weather

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    weatherState: WeatherState,
    backgroundImage: Int,
    resId: Int
) {
    val painter = painterResource(id = backgroundImage)
    val stateValue by remember(weatherState) { mutableStateOf(weatherState) }
    val animatedComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.weather_cloudynight))
    val localLottieIterations = compositionLocalOf { LottieAnimationIterations(IterateForever) }
    var stateLocationValue by remember(weatherState.weatherInfo.location) { mutableStateOf(weatherState.weatherInfo.location) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(21, 153, 247, 255),
        content = {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    fontSize = 32.sp,
                    text = "Type any place you want"
                )

                WeatherContainer(
                    weatherData = stateValue.weatherInfo
                )

                LottieAnimation(
                    composition =  animatedComposition,
                    speed = 0.5f,
                    iterations = localLottieIterations.current.iterations
                )

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
            }
        }
    )
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    weatherState: WeatherState,
    submit: (String) -> Unit,
) {
    var stateLocationValue by remember(weatherState.weatherInfo.location) { mutableStateOf(weatherState.weatherInfo.location) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(21, 153, 247, 255),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            ) {
                Text(
                    text = "My Search Screen",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )

                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(82.dp)
                        .padding(16.dp)
                        .wrapContentHeight(align = CenterVertically)
                        .background(Color.White),
                    value = stateLocationValue,
                    placeholder = { stringResource(id = R.string.placeholder_text_field_search_location) },
                    onValueChange = {
                        stateLocationValue = it
                    }
                )

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .wrapContentHeight(align = CenterVertically),
                    onClick = {
                        submit(stateLocationValue ?: "")
                    }
                ) {
                    Text(text = "Search")
                }

                WeatherRow(
                    weatherState.weatherRowViewEntity
                )
            }
        }
    )
}

@JvmInline
value class LottieAnimationIterations(val iterations: Int)

@Composable
fun WeatherRow(
    weatherRowViewEntity: WeatherRowViewEntity?,
) {


    weatherRowViewEntity?.let {
        val animatedComposition by rememberLottieComposition(it.icon)
        val localLottieIterations = compositionLocalOf { LottieAnimationIterations(IterateForever) }
        Row(
            modifier = Modifier
                .background(Color.Green, RoundedCornerShape(16))
                .fillMaxWidth()
                .padding(32.dp)
                .height(58.dp)
        ) {
            LottieAnimation(
                modifier = Modifier.size(80.dp),
                composition =  animatedComposition,
                iterations = localLottieIterations.current.iterations
            )
            Text(
                text = it.location,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.align(CenterVertically),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun WeathersScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.teal_700))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Weathers Screen",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}


@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.teal_700))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Settings Screen",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}

@Composable
fun WeatherContainer(
    weatherData: Weather
) {
    Column(
        Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            fontSize = 32.sp,
            text = weatherData.location
        )
    }
}