package com.example.superweather.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.superweather.ui.screens.components.InformationComponent
import com.example.superweather.ui.screens.components.LoadingComponent
import com.example.superweather.ui.screens.components.WeatherRow
import com.leumas.superweather.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeathersScreen(
    favoriteState: FavoriteState,
    weatherState: WeatherState,
    isOpenedBottomSheet: Boolean,
    onItemClicked: () -> Unit,
    onRemoveItemClicked: (List<WeatherRowViewEntity>) -> Unit,
    onRefresh: () -> Unit,
    onWeatherInfoEmptyButtonClick: () -> Unit,
    onDismissBottomSheetRequest: () -> Unit,
) {
    var isUndoDialogVisible by remember { mutableStateOf(false) }
    var currentProgress by remember { mutableFloatStateOf(0f) }
    var loading by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()
    val deletedRows = remember { mutableStateListOf<WeatherRowViewEntity>() }

    //WAIT for 5 seconds and then hide the dialog and delete the items
    LaunchedEffect(isUndoDialogVisible) {
        loading = true
        CoroutineScope(Dispatchers.IO).launch {
            loadProgress { progress ->
                currentProgress = progress
            }
            currentProgress = 100f
            loading = false
            isUndoDialogVisible = false
            onRemoveItemClicked(deletedRows)
        }
    }

    LaunchedEffect(true) {
        onRefresh()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 64.dp)
    ) {
        if (favoriteState.isLoading) {
            LoadingComponent()
        } else {
            LazyColumn(
                state = lazyListState,
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = favoriteState.weatherRows,
                ) { row ->
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
                                onItemClicked()
                            },
                            onFavoriteClicked = {
                                deletedRows.add(row)
                                isUndoDialogVisible = true
                            },
                            isFavoriteFlow = true
                        )
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            AnimatedVisibility(
                visible = isUndoDialogVisible,
                enter = expandHorizontally(
                    animationSpec = tween(
                        durationMillis = 1000,
                    )
                ),
                exit = shrinkHorizontally(
                    animationSpec = tween(
                        durationMillis = 1000,
                    )
                )
            ) {
                Column {
                    InformationComponent(
                        modifier = Modifier,
                        message = stringResource(id = R.string.item_deleted_information),
                        icon = Icons.Default.Delete,
                        onClickButton = {
                            isUndoDialogVisible = false
                            deletedRows.clear()
                        },
                        buttonText = stringResource(id = R.string.common_undo)
                    )
                    if (loading) {
                        LinearProgressIndicator(
                            progress = { currentProgress },
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                            color = Color.Green,
                            trackColor = Color.White,
                        )
                    }
                }
            }

            Button(
                modifier = Modifier
                    .padding(bottom = 16.dp),
                onClick = {
                    onRefresh()
                }
            ) {
                Text(text = stringResource(id = R.string.common_refresh))
            }

        }
    }

    if (isOpenedBottomSheet) {
        ModalBottomSheet(
            modifier = Modifier.padding(top = 25.dp),
            onDismissRequest = { onDismissBottomSheetRequest() },
            containerColor = Color(21, 153, 247, 222),
        ) {
            HomeScreen(weatherState = weatherState) {
                onWeatherInfoEmptyButtonClick()
            }
        }
    }
}

suspend fun loadProgress(updateProgress: (Float) -> Unit) {
    for (i in 1..100) {
        updateProgress(i.toFloat() / 100)
        delay(100)
    }
}
