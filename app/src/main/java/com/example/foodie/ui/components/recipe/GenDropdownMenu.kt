package com.example.foodie.ui.components.recipe

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.foodie.data.model.categories.CategoriesResponse

@Composable
fun GenDropdownMenu(
    items: List<CategoriesResponse>,
    selectedItem: CategoriesResponse,
    selectedItems: List<CategoriesResponse>,
    modifier: Modifier = Modifier,
    defaultLabel: String
) {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Box(modifier = modifier) {
        Surface(color = Color.Transparent, contentColor = Color.White) {
        Text(
            text = selectedItem.name.ifEmpty { defaultLabel },
            modifier = Modifier
                .fillMaxWidth()
                .height(25.dp)
                .size(16.dp)
                .clickable { expanded = true },
            color = Color.White
        )
    }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
                .background(Color.Black)
        ) {
            items.forEach { item ->
                if (!selectedItems.any { it.id == item.id }) {
                    DropdownMenuItem(
                        onClick = {
                            selectedItem.id = item.id
                            selectedItem.name = item.name
                            selectedItem.description = item.description
                            expanded = false
                        },
                        modifier = Modifier.background(Color.Transparent)
                    ) {
                        Text(
                            text = item.name.toString(),
                            modifier = Modifier.fillMaxWidth(),
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
