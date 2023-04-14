package com.example.foodie.ui.recipe

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.foodie.ui.components.recipe.AddIngredientRow
import com.example.foodie.ui.components.recipe.IngredientList

@Composable
fun RecipeScreen(viewModel: RecipeViewModel, navigationController: NavHostController) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val input = remember { mutableStateOf(TextFieldValue()) }
        AddIngredientRow(input = input, onAddClick = { viewModel.addIngredient(input.value.text) })
        Spacer(Modifier.height(8.dp))
        IngredientList(
            ingredients = viewModel.ingredientList,
            onRemoveClick = { viewModel.removeIngredient(it) },
            onRetryClick = { viewModel.retryGetIngredientUrl(it) },
            recipeViewModel = viewModel
        )
    }
}
