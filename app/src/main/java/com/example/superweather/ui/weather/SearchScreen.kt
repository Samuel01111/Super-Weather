package com.example.superweather.ui.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.superweather.ui.weather.components.LocationPointerAnimation
import com.example.superweather.ui.weather.components.SearchTextField
import com.example.superweather.ui.weather.components.WeatherRow

@Composable
fun SearchScreen(
    searchState: WeatherState,
    currentLocationState: WeatherState,
    submit: (String) -> Unit,
    onSearchLocationRowClicked: () -> Unit,
    onCurrentLocationRowClicked: () -> Unit,
    onRequestLocalizationPermissionClicked: () -> Unit,
    isLocationPermissionActive: Boolean
) {
    var stateLocationValue by remember(searchState.weatherInfo.location) { mutableStateOf(searchState.weatherInfo.location) }
    val focusManager = LocalFocusManager.current

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
            .background(Color(21, 153, 247, 255)),
        color = Color(21, 153, 247, 255),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = CenterHorizontally
            ) {
                Text(
                    text = "Search",
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
                        focusManager.clearFocus()
                    }
                )
                WeatherRow(
                    viewEntity = searchState.weatherRowViewEntity,
                    onItemClicked = { onSearchLocationRowClicked() }
                )

                if (currentLocationState.isCurrentLocation) {
                    Row(
                        modifier = Modifier.align(Start)
                    ) {
                        Text(
                            text = "My Location",
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier
                                .padding(bottom = 6.dp),
                            textAlign = TextAlign.Center,
                            fontSize = 28.sp
                        )

                        LocationPointerAnimation(
                            Modifier
                                .size(50.dp)
                                .align(Alignment.Bottom)
                                .defaultMinSize(minWidth = 25.dp)
                                .padding(top = 4.dp),
                        )
                    }
                    WeatherRow(
                        viewEntity = currentLocationState.weatherRowViewEntity,
                        onItemClicked = { onCurrentLocationRowClicked() }
                    )
                }

                if (!isLocationPermissionActive) {
                    Spacer(modifier = Modifier.height(32.dp))
                    RequestLocationScreen(onRequestLocalizationPermissionClicked)
                }
            }
        }
    )
}

@Composable
fun RequestLocationScreen(
    onRequestLocalizationPermissionClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .background(
                Color(36, 29, 29, 50),
                shape = RoundedCornerShape(12)
            )
            .clickable {
                onRequestLocalizationPermissionClicked()
            }
    ) {
        Text(
            text = "Location Not Found",
            fontWeight = FontWeight.Bold,
            color = Color.Red,
            modifier = Modifier
                .padding(bottom = 6.dp, top = 16.dp),
            textAlign = TextAlign.Center,
            fontSize = 12.sp
        )
        LocationPointerAnimation(
            Modifier
                .size(50.dp)
                .align(Alignment.Bottom)
                .defaultMinSize(minWidth = 25.dp)
                .padding(top = 4.dp),
        )
    }
}
