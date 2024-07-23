package com.example.superweather.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leumas.superweather.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    errorMessage: String?
) {
    val focusManager = LocalFocusManager.current
    
    Column(
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .background(Color.White, RoundedCornerShape(4))
        ) {
            TextField(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp)
                    .background(Color.White, RoundedCornerShape(12)),
                maxLines = 1,
                value = value,
                onValueChange = onValueChange,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Go),
                keyboardActions = KeyboardActions(onGo = {
                    onSearchClick()
                    focusManager.clearFocus()
                }),
                placeholder = { Text(text = stringResource(id = R.string.placeholder_text_field_search_location)) },
                colors = textFieldColors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    errorTextColor = Color.Red,
                    errorContainerColor = Color.White,
                    errorCursorColor = Color.Red,
                    errorIndicatorColor = Color.Red,
                    errorLabelColor = Color.Red,
                    errorTrailingIconColor = Color.Red,
                    errorPlaceholderColor = Color.Red,
                    focusedLeadingIconColor = Color.Red,
                    unfocusedLeadingIconColor = Color.Red,
                    cursorColor = Color.Black
                ),
                isError = errorMessage != null
            )

            IconButton(
                onClick = onSearchClick,
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.search_icon_content_description),
                    tint = Color.Gray
                )
            }
        }
        if (errorMessage != null) {

            Row(
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(12)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.padding(start = 16.dp),
                    painter = painterResource(id = R.drawable.ic_error),
                    contentDescription = stringResource(id = R.string.search_icon_content_description),
                    tint = Color.Red
                )
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp),
                    text = errorMessage,
                    color = Color.Red,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
@Preview
fun SearchTextFieldPreview() {
    SearchTextField(
        value = "Londres",
        onValueChange = {},
        onSearchClick = {},
        errorMessage = null
    )
}
@Composable
@Preview
fun SearchTextFieldPreviewError() {
    SearchTextField(
        value = "Haxixe to president (Haxixe is my dog)",
        onValueChange = {},
        onSearchClick = {},
        errorMessage = "Here is where error message will be shown"
    )
}
