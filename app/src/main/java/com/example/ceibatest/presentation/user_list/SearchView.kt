package com.example.ceibatest.presentation.user_list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp


@Composable
fun SearchView(state: MutableState<TextFieldValue>) {

    TextField(
        value = state.value, onValueChange = { value ->
            state.value = value
        },
        label = { Text(text = "Buscar usuario") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent

        )
    )
}
