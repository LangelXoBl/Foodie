package com.example.foodie.ui.components.recipe

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.ExposedDropdownMenuDefaults.outlinedTextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InputWithButton(
    label: String,
    buttonText: String,
    icon: ImageVector,
    contentDescription: String,
    onButtonClick: (String) -> Unit,
    customOutlinedTextFieldColors: TextFieldColors
) {
    val input = remember { mutableStateOf(TextFieldValue()) }
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = input.value,
            onValueChange = { input.value = it },
            label = { Text(label, color = Color.White) },
            modifier = Modifier.weight(1f),
            colors = customOutlinedTextFieldColors

        )
        IconButton(
            onClick = {
                onButtonClick(input.value.text)
                input.value = TextFieldValue()
            },
            modifier = Modifier
                .padding(bottom = 8.dp)
        ) {
            Icon(icon, contentDescription = contentDescription, tint = Color.White)
        }
    }
}

