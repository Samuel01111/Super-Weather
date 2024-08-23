package com.example.superweather.ui.screens.utils

import kotlinx.coroutines.delay

suspend fun loadProgress(isUndoDialogVisible: Boolean, updateProgress: (Float) -> Unit = {}) {
    if (isUndoDialogVisible) {
        for (i in 1..100) {
            updateProgress(i.toFloat() / 100)
            delay(50)
        }
    }
}
