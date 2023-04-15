package com.example.foodie.ui.components.recipe

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun AddIngredientRow(input: MutableState<TextFieldValue>, onAddClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = input.value,
            onValueChange = { input.value = it },
            label = { Text("Ingrediente") },
            modifier = Modifier.weight(1f)
        )
        IconButton(onClick = {
            onAddClick()
            input.value = TextFieldValue()
        }) {
            Icon(Icons.Default.Add, contentDescription = "Añadir ingrediente")
        }
    }
}