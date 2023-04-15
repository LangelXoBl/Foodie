package com.example.foodie.ui.components.recipe

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import com.example.foodie.ui.recipe.RecipeViewModel

@Composable
fun IngredientList(recipeViewModel: RecipeViewModel) {
    val ingredients = recipeViewModel.ingredientList
    Column {
        ingredients.forEach { ingredient ->
            IngredientRow(
                ingredient,
                recipeViewModel
            )
            Divider()
        }
    }
}
