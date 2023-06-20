package com.example.superweather.ui.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.superweather.ui.weather.components.SearchTextField
import com.example.superweather.ui.weather.components.WeatherRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    weatherState: WeatherState,
    submit: (String) -> Unit,
) {
    var stateLocationValue by remember(weatherState.weatherInfo.location) { mutableStateOf(weatherState.weatherInfo.location) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
            .background(Color(9, 21, 61, 255)),
        color = Color(9, 21, 61, 255),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = CenterHorizontally
            ) {
                Text(
                    text = "Weather",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .align(Start)
                        .padding(bottom = 6.dp, top = 16.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 28.sp
                )

                SearchTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = stateLocationValue,
                    onValueChange = { newValue ->
                        stateLocationValue = newValue
                    },
                    onSearchClick = {
                        submit(stateLocationValue)
                    }
                )

                WeatherRow(
                    weatherState.weatherRowViewEntity
                )
            }
        }
    )
}

@JvmInline
value class LottieAnimationIterations(val iterations: Int)