package com.example.foodie.data.model.recipe

import androidx.compose.ui.text.input.TextFieldValue

class TextFieldAtributes(
    val value: TextFieldValue,
    val onValueChange: (TextFieldValue) -> Unit,
    val label: String
)