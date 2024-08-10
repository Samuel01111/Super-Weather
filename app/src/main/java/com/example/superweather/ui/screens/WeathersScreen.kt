package com.example.superweather.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.superweather.ui.screens.components.PullToRefreshLazyColumn
import com.example.superweather.ui.screens.components.WeatherRow
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeathersScreen(
    favoriteState: FavoriteState,
    onItemClicked: () -> Unit = {},
    onRefresh: List<WeatherRowViewEntity>
) {
    val isRefreshing by remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()
    val deletedRows = remember { mutableListOf<Any>() }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        PullToRefreshLazyColumn(
            items = favoriteState.weatherRows,
            content = { row ->
                AnimatedVisibility(
                    visible = !deletedRows.contains(row),
                    enter = expandVertically(),
                    exit = shrinkVertically(
                        animationSpec = tween(
                            durationMillis = 1000,
                        )
                    )
                ) {
                    WeatherRow(
                        viewEntity = row,
                        onItemClicked = {
                            deletedRows.add(row)
                            onItemClicked()
                        },
                        isFavoriteFlow = true
                    )
                }
            },
            isRefreshing = isRefreshing,
            onRefresh = {
                scope.launch {
                    favoriteState.
                }
            }
        )
    }

    Column {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            itemsIndexed(
                items = favoriteState.weatherRows,
                itemContent = {_, row ->
                    AnimatedVisibility(
                        visible = !deletedRows.contains(row),
                        enter = expandVertically(),
                        exit = shrinkVertically(
                            animationSpec = tween(
                                durationMillis = 1000,
                            )
                        )
                    ) {
                        WeatherRow(
                            viewEntity = row,
                            onItemClicked = {
                                deletedRows.add(row)
                                onItemClicked()
                            },
                            isFavoriteFlow = true
                        )
                    }
                }
            )
        }
    }
}
