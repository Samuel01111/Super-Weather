package com.example.superweather.ui.screens.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leumas.superweather.R

@Composable
fun WeatherInfoEmptyComponent(
    modifier: Modifier = Modifier,
    onClickButton: () -> Unit
) {
    Column(
        modifier = modifier
            .wrapContentSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.weather_info_empty),
            fontSize = 20.sp,
            color = Color(0xFF, 0xFF, 0xFF, 0xFF)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.weather_info_empty_button_title),
            fontSize = 16.sp,
            color = Color(0xFF, 0xFF, 0xFF, 0xFF),
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = Color(0xFF, 0xFF, 0xFF, 0xFF),
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable { onClickButton() }
                .padding(8.dp)
        )
    }
}

@Preview
@Composable
private fun WeatherInfoEmptyComponentPreview() {
    WeatherInfoEmptyComponent(
        onClickButton = {}
    )
}
