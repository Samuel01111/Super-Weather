package com.example.superweather.ui.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.superweather.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    weatherState: WeatherState.Submit,
    backgroundImage: Int
) {
    val painter = painterResource(id = backgroundImage)
    val stateValue = remember(weatherState.textLocation) { mutableStateOf(weatherState.textLocation) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
        content = {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painter,
                contentDescription = null
            )

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(Color.White),
                value = weatherState.textLocation ?: "",
                placeholder = { stringResource(id = R.string.placeholder_text_field_search_location) },
                onValueChange = {
                    weatherState.textLocation = it
                }
            )


            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(align = CenterVertically),
                onClick = {
                    weatherState.textLocation = null
                }
            ) {

            }

        }
    )
}