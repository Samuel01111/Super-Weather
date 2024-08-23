package com.example.superweather.ui.screens.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.leumas.superweather.R

@Composable
fun DeleteFavoriteComponent(
    onFavoriteClicked: () -> Unit = {},
) {
    Box(
        modifier = Modifier.wrapContentSize(),
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            onClick = { onFavoriteClicked() },
            modifier = Modifier.align(Alignment.Center)
        ) {
            Icon(
                modifier = Modifier.size(100.dp),
                imageVector = Icons.Default.Delete,
                contentDescription = stringResource(id = R.string.search_icon_content_description),
                tint = Color.Black
            )
        }
    }
}
