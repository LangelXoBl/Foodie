package com.example.foodie.ui.components.recipe

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Divider
import androidx.compose.runtime.*
import com.example.foodie.data.model.recipe.RecipeResponse
import com.example.foodie.ui.recipe.RecipeViewModel

@Composable
fun IngredientList(
    ingredients: List<RecipeResponse>,
    onRemoveClick: (RecipeResponse) -> Unit,
    onRetryClick: (RecipeResponse) -> Unit,
    recipeViewModel: RecipeViewModel
) {
    var updatedIngredients by remember { mutableStateOf(ingredients) }

    LaunchedEffect(key1 = ingredients) {
        updatedIngredients = ingredients
    }
    Column {
        updatedIngredients.forEach { ingredient ->
            IngredientRow(
                ingredient,
                onRetryClick = { onRetryClick(ingredient) },
                onRemoveClick = { onRemoveClick(ingredient) }
            )
            Divider()
        }
    }
}
