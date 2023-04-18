package com.example.foodie.ui.components.recipe

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.foodie.data.model.ingredient.IngredientResponse
import com.example.foodie.data.model.recipe.RecipeResponse
import com.example.foodie.ui.recipe.RecipeViewModel


@Composable
fun IngredientRow(
    ingredient: IngredientResponse,
    recipeViewModel: RecipeViewModel
) {
    val context = LocalContext.current
    fun onRetryClick() {
        recipeViewModel.retryGetIngredientUrl(ingredient)
    }
    fun onRemoveClick() {
        recipeViewModel.removeIngredient(ingredient)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = ingredient.name,
            modifier = Modifier.weight(1f),
            color = Color.White
        )

        when (ingredient.status) {
            IngredientResponse.Status.LOADING -> {
                Text(text = "Cargando...", color = Color.White)
            }
            IngredientResponse.Status.SUCCESS -> {
                Icon(
                    Icons.Filled.Visibility,
                    contentDescription = "Ver ingrediente",
                    modifier = Modifier
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(ingredient.url))
                            context.startActivity(intent)
                        },
                    tint = Color.White
                )

            }
            IngredientResponse.Status.ERROR -> {
                Icon(
                    Icons.Filled.Refresh,
                    contentDescription = "Reintentar",
                    modifier = Modifier.clickable {
                        onRetryClick()
                    },
                    tint = Color.White
                )

            }
            else -> {}
        }
        Icon(Icons.Filled.Clear,
            contentDescription = "Eliminar",
            modifier = Modifier
                .clickable { onRemoveClick() }
                .padding(start = 16.dp),
            tint = Color.White
        )
    }
}
