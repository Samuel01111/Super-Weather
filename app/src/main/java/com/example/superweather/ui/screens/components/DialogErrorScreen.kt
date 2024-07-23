package com.example.superweather.ui.screens.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.superweather.ui.theme.BlueGood

@Composable
fun DialogError(
    modifier: Modifier = Modifier,
    title: String,
    message: String,
    textConfirmButton: String,
    onDismissDialog: () -> Unit,
) {
    //Center the title and message
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onDismissDialog() },
        confirmButton = {
            Button(
                onClick = { onDismissDialog() },
                colors = ButtonColors(BlueGood, Color.White, Color.Gray, Color.DarkGray)
            ) {
                Text(text = textConfirmButton)
            }
        },
        title = { Text(text = title) },
        text = { Text(text = message) },
    )
}

@Preview
@Composable
fun DialogErrorPreview() {
    DialogError(
        title = "Error on request",
        message = "Error in the server, try again later!",
        textConfirmButton = "Ok",
        onDismissDialog = {}
    )
}
