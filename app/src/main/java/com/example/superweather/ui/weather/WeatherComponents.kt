package com.example.superweather.ui.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.superweather.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    weatherState: WeatherState.Submit,
    backgroundImage: Int
) {
    val painter = painterResource(id = backgroundImage)
    val stateValue by remember(weatherState) { mutableStateOf(weatherState) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
        content = {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                WeatherContainer(
                    weatherData = stateValue.weatherData
                )

                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(82.dp)
                        .padding(16.dp)
                        .wrapContentHeight(align = CenterVertically)
                        .background(Color.White),
                    value = stateValue.textLocation ?: "",
                    placeholder = { stringResource(id = R.string.placeholder_text_field_search_location) },
                    onValueChange = {
                        stateValue.textLocation = it
                    }
                )

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .wrapContentHeight(align = CenterVertically),
                    onClick = {
                        stateValue.textLocation = null
                        stateValue.textLocationError = null
                    }
                ) {
                    Text(text = "Search")
                }
            }
        }
    )
}
@Composable
fun WeatherContainer(
    weatherData: WeatherData
) {
    Column(
        Modifier
            .background(Color.Transparent),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            fontSize = 32.sp,
            text = weatherData.high
        )

        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            fontSize = 72.sp,
            text = weatherData.temperature
        )

        Text(
            modifier = Modifier.padding(vertical = 4.dp),
            fontSize = 24.sp,
            text = weatherData.condition
        )

        Row {
            Text(text = "H:" + weatherData.high)
            Text(text = "L:" + weatherData.low)
        }
    }
}