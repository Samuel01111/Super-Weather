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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.superweather.ui.screens.components.InformationComponent
import com.example.superweather.ui.screens.components.LoadingComponent
import com.example.superweather.ui.screens.components.WeatherRow
import com.example.superweather.ui.screens.utils.loadProgress
import com.leumas.superweather.BuildConfig
import com.leumas.superweather.R
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeathersScreen(
    favoriteState: FavoriteState,
    weatherState: WeatherState,
    isOpenedBottomSheet: Boolean,
    onItemClicked: (WeatherRowViewEntity) -> Unit = {},
    onRemoveItemClicked: (List<WeatherRowViewEntity>) -> Unit = {},
    onRefresh: () -> Unit = {},
    onWeatherInfoEmptyButtonClick: () -> Unit = {},
    onDismissBottomSheetRequest: () -> Unit = {},
) {
    var isUndoDialogVisible by remember { mutableStateOf(false) }
    var isEmptyTitleVisible by remember { mutableStateOf(false) }
    var currentProgress = remember { mutableFloatStateOf(0.00f) }
    var progressJob: Job? by remember { mutableStateOf(null) }

    val scope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()
    val deletedRows = remember { mutableStateListOf<WeatherRowViewEntity>() }

    LaunchedEffect(isUndoDialogVisible) {
        progressJob?.cancel()

        progressJob = scope.launch {
            loadProgress(isUndoDialogVisible) { progress ->
                currentProgress.floatValue = progress
            }
            if (isUndoDialogVisible && currentProgress.floatValue >= 1f) {
                onRemoveItemClicked(deletedRows.toList())
                onRefresh()
                isUndoDialogVisible = false
            }
        }
    }

    LaunchedEffect(true) {
        onRefresh()
    }

    LaunchedEffect(favoriteState) {
        isEmptyTitleVisible = favoriteState.weatherRows.isEmpty()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 64.dp)
    ) {
        if (favoriteState.isLoading) {
            LoadingComponent()
        } else {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.weather_screen_title),
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontSize = 28.sp
                    )
                    Column(
                        modifier = Modifier
                            .wrapContentSize(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Version Code: ${BuildConfig.VERSION_CODE}",
                            fontWeight = FontWeight.Medium,
                            color = Color.LightGray,
                            fontSize = 12.sp
                        )

                        Text(
                            text = "Version Name: ${BuildConfig.VERSION_NAME}",
                            fontWeight = FontWeight.Medium,
                            color = Color.LightGray,
                            fontSize = 12.sp
                        )
                    }
                }

                AnimatedVisibility(
                    visible = isEmptyTitleVisible,
                    enter = expandVertically(
                        animationSpec = tween(
                            durationMillis = 1000,
                        )
                    ),
                    exit = shrinkVertically(
                        animationSpec = tween(
                            durationMillis = 1000,
                        )
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.weather_search_for_more),
                        fontWeight = FontWeight.Medium,
                        color = Color.White,
                        modifier = Modifier.padding(),
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp
                    )
                }

                LazyColumn(
                    state = lazyListState,
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
                                    onItemClicked(row)
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
                        durationMillis = 500,
                    )
                ),
                exit = shrinkHorizontally(
                    animationSpec = tween(
                        durationMillis = 500,
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
                    LinearProgressIndicator(
                        progress = { currentProgress.floatValue },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 26.dp),
                        color = Color.White,
                        trackColor = Color.Green,
                    )
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
