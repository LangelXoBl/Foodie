package com.example.foodie.ui.components.recipe

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foodie.data.model.recipe.RecipeResponse
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Visibility


@Composable
fun IngredientRow(
    ingredient: RecipeResponse,
    onRetryClick: () -> Unit,
    onRemoveClick: () -> Unit
) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = ingredient.name,
            modifier = Modifier.weight(1f)
        )

        when (ingredient.status) {
            RecipeResponse.Status.LOADING -> {
                Text(text = "Cargando...")
            }
            RecipeResponse.Status.SUCCESS -> {
                Icon(
                    Icons.Filled.Visibility,
                    contentDescription = "Ver ingrediente",
                    modifier = Modifier.clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(ingredient.url))
                        context.startActivity(intent)
                    }
                )

            }
            RecipeResponse.Status.ERROR -> {
                Icon(
                    Icons.Filled.Refresh,
                    contentDescription = "Reintentar",
                    modifier = Modifier.clickable {
                        onRetryClick()
                    }
                )

            }
            else -> {}
        }
            Icon(Icons.Filled.Clear,
                contentDescription = "Eliminar",
                modifier = Modifier
                    .clickable { onRemoveClick() }
                    .padding(start = 16.dp)
            )
    }
}
