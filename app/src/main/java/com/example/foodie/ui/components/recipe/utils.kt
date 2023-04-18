package com.example.foodie.ui.components.recipe

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.foodie.R

@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    customOutlinedTextFieldColors: TextFieldColors
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = Color.White) },
        modifier = modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth(1f),
        colors = customOutlinedTextFieldColors
    )
}


@Composable
fun ExpandableContent(title: String, content: @Composable () -> Unit) {
    var isExpanded by remember { mutableStateOf(false) }

    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            color = Color.White
        )

        IconButton(onClick = { isExpanded = !isExpanded }) {
            if (isExpanded) {
                Icon(
                    imageVector = Icons.Filled.ArrowDropUp,
                    contentDescription = stringResource(R.string.hide),
                    tint = Color.White
                )
            } else {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = stringResource(R.string.show),
                    tint = Color.White
                )
            }
        }
    }

    if (isExpanded) {
        Column(modifier = Modifier.animateContentSize()) {
            content()
        }
    }
}