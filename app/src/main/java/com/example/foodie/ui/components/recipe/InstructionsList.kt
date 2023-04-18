package com.example.foodie.ui.components.recipe

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.foodie.ui.recipe.RecipeViewModel


@Composable
fun InstructionsList(recipeViewModel: RecipeViewModel) {
    val instructions = recipeViewModel.instructionsList
    val onRemoveCategory = recipeViewModel::removeInstruction
    Column {
        instructions.forEachIndexed { index, category ->
            InstructionRow(
                category,
                index,
                onRemoveCategory
            )
            Divider(color = Color.White)

        }
    }
}

@Composable
fun InstructionRow(
    category: String,
    index: Int,
    onRemoveCategory:  (String) -> Unit
) {
    val step = index + 1
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = category,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .weight(8f)
                .padding(8.dp),
            color = Color.White
        )
        IconButton(
            onClick = {
                onRemoveCategory(category)
            }
        ) {
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = "Clear Category",
                tint = Color.White
            )
        }
    }
}