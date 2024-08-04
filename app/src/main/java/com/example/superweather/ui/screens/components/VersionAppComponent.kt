package com.example.superweather.ui.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun VersionAppComponent(
    versionCode: String,
    versionName: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 64.dp)
            .wrapContentSize(Alignment.BottomEnd)
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = versionCode,
            fontSize = 12.sp,
            color = Color(255, 255, 255, 87),
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = versionName,
            fontSize = 12.sp,
            color = Color(255, 255, 255, 87),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun VersionAppComponentPreview() {
    VersionAppComponent("6", "1.2")
}