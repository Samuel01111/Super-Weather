package com.example.superweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.superweather.ui.MainViewModel
import com.example.superweather.ui.di.MainComponent
import com.example.superweather.ui.theme.SuperWeatherTheme
import com.example.superweather.ui.weather.WeatherScreen
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    private lateinit var mainComponent: MainComponent

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<MainViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        mainComponent = (applicationContext as WeatherApplication)
            .appComponent
            .mainComponent()
            .create()
        
        super.onCreate(savedInstanceState)
        setContent {
            SuperWeatherTheme {
                WeatherScreen(
                    backgroundImage = R.drawable.clounds,
                    weatherState = viewModel.state,
                    submit = { viewModel.fetchWeatherByName(it) }
                )
            }
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        mainComponent
            .inject(this)
    }
}
