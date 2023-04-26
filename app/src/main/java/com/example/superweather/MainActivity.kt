package com.example.superweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.superweather.ui.theme.SuperWeatherTheme
import com.example.superweather.ui.weather.WeatherScreen
import com.example.superweather.ui.weather.WeatherState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperWeatherTheme {
                WeatherScreen(
                    backgroundImage = R.drawable.nyc,
                    weatherState = WeatherState.Submit(
                        textLocation = "OPA",
                        textLocationError = null)
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SuperWeatherTheme {
        Greeting("Android")
    }
}