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
import com.example.foodie.data.model.categories.CategoriesResponse
import com.example.foodie.ui.recipe.RecipeViewModel

@Composable
fun CategoriesList(
    recipeViewModel: RecipeViewModel
) {
    val categories = recipeViewModel.selectedCategories
    val onRemoveCategory = recipeViewModel::removeCategory
    Column {
        categories.forEachIndexed { index, category ->
            CategoryRow(
                category,
                index,
                onRemoveCategory
            )
            Divider(color = Color.White)
        }
    }
}

@Composable
fun CategoryRow(
    category: CategoriesResponse,
    index: Int,
    onRemoveCategory:  (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "${index + 1}.- ${category.name}",
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .weight(8f)
                .padding(8.dp),
            color = Color.White
        )
        IconButton(
            onClick = {
                onRemoveCategory(category.name)
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