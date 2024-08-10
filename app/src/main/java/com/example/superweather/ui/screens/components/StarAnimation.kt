package com.example.superweather.ui.screens.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.leumas.superweather.R

@Composable
fun StarFavoriteComponent(
    onFavoriteClicked: (Boolean) -> Unit,
    isFavorite: Boolean
) {
    var isFavorite by remember { mutableStateOf(isFavorite) }
    var favoriteIcon by remember { mutableStateOf(Icons.Default.Favorite) }

    favoriteIcon = if (isFavorite) {
        Icons.Default.Favorite
    } else {
        Icons.Default.FavoriteBorder
    }

    Box(
        modifier = Modifier.wrapContentSize(),
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            onClick = {
                isFavorite = !isFavorite
                onFavoriteClicked(isFavorite)
            },
            modifier = Modifier.align(Alignment.Center)
        ) {
            Icon(
                modifier = Modifier.size(180.dp),
                imageVector = favoriteIcon,
                contentDescription = stringResource(id = R.string.search_icon_content_description),
                tint = Color.Yellow
            )
        }
    }
}
