package com.example.superweather.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.superweather.ui.theme.BlueGood
import com.leumas.superweather.R

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