package com.example.superweather.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.superweather.ui.screens.components.LocationPointerAnimation
import com.example.superweather.ui.screens.components.SearchTextField
import com.example.superweather.ui.screens.components.WeatherRow
import com.example.superweather.ui.theme.BlueGood
import com.leumas.superweather.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    searchState: WeatherState,
    currentLocationState: WeatherState,
    submit: (String) -> Unit,
    onSearchLocationRowClicked: () -> Unit,
    onCurrentLocationRowClicked: () -> Unit,
    onRequestLocalizationPermissionClicked: () -> Unit,
    isLocationPermissionActive: Boolean,
    isSearching: Boolean,
    isOpenedBottomSheet: Boolean,
    onDismissBottomSheetRequest: () -> Unit,
    clearError: () -> Unit
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
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.search_title_text_field),
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .align(Start)
                        .padding(bottom = 6.dp, top = 16.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 28.sp
                )
                SearchTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = stateLocationValue,
                    onValueChange = { newValue ->
                        stateLocationValue = newValue
                        clearError()
                    },
                    onSearchClick = {
                        if (!stateLocationValue.isEmpty()) {
                            submit(stateLocationValue)
                            focusManager.clearFocus()
                        }
                    },
                    errorMessage = searchState.error
                )
                Spacer(modifier = Modifier.padding(16.dp))
                WeatherRow(
                    viewEntity = searchState.weatherRowViewEntity,
                    onItemClicked = { onSearchLocationRowClicked() },
                    isSearching = isSearching
                )

                if (currentLocationState.isCurrentLocation) {
                    Row(
                        modifier = Modifier.align(Start)
                    ) {
                        Text(
                            text = stringResource(id = R.string.my_location_title_container),
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.padding(bottom = 6.dp),
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
    if (isOpenedBottomSheet) {
        ModalBottomSheet(
            modifier = Modifier.padding(top = 25.dp),
            onDismissRequest = { onDismissBottomSheetRequest() },
            containerColor = Color(21, 153, 247, 222),
        ) {
            HomeScreen(weatherState = searchState)
        }
    }
}

@Composable
fun RequestLocationScreen(onRequestLocalizationPermissionClicked: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = BlueGood,
                shape = RoundedCornerShape(12),
            )
            .border(
                width = 1.dp,
                color = Color.White,
                shape = RoundedCornerShape(12)
            )
            .clickable { onRequestLocalizationPermissionClicked() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.location_not_found_text),
            color = Color.White,
            modifier = Modifier.padding(start = 8.dp),
            textAlign = TextAlign.Center,
            fontSize = 12.sp
        )
        LocationPointerAnimation(
            Modifier
                .size(50.dp)
                .align(Alignment.CenterVertically)
                .defaultMinSize(minWidth = 25.dp),
        )
    }
}

@Composable
@Preview
fun RequestLocationScreenPreview() {
    RequestLocationScreen{}
}
