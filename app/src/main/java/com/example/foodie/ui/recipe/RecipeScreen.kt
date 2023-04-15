package com.example.foodie.ui.recipe

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foodie.ui.components.recipe.AddIngredientRow
import com.example.foodie.ui.components.recipe.IngredientList

@Composable
fun RecipeScreen(recipeViewModel: RecipeViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AddIngredientRow(recipeViewModel = recipeViewModel)
        Spacer(Modifier.height(8.dp))
        IngredientList(recipeViewModel = recipeViewModel)
    }
}
