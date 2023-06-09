package com.example.foodie.ui.components.recipe

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Sell
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.foodie.data.model.recipe.RecipeResponse
import com.example.foodie.navigation.ItemsNav
import com.example.foodie.ui.home.ListRecipesViewModel

//@Preview()
@Composable
fun CardRecipe(
    recipe: RecipeResponse,
    navController: NavController,
    listRecipesViewModel: ListRecipesViewModel,
    page:String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { navController.navigate(ItemsNav.RecipeDetail.route + "/${recipe.id}") },
        elevation = 10.dp
    ) {
        Row(Modifier.fillMaxWidth()) {
            RecipeImage(
                recipe.image,
                Modifier
                    .size(150.dp)
                    .border(BorderStroke(1.dp, Color.Black))
            )
            Column(
                Modifier
                    .weight(1f)
                    .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 20.dp)
            ) {
                RecipeTitle(recipe.title)
                Divider()
                RecipeCategories(recipe.categories)
                RecipeDescription(recipe.description)
                RecipePreparationTime(recipe.preparation_time)

                FavoriteIcon(Modifier.align(End), recipe.favorite, listRecipesViewModel, recipe.id, page)
            }
        }
    }
}

@Composable
fun RecipeImage(url: String, modifier: Modifier) {
    AsyncImage(
        model = url,
        contentDescription = "Pizza",
        modifier = modifier
    )
}

@Composable
fun RecipeTitle(title: String) {
    Text(
        text = title,
        style = TextStyle(fontWeight = FontWeight.W900, color = Color(0xFF4552B8)),
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
fun RecipeCategories(categories: List<String>) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Default.Sell,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = categories.joinToString(),
            style = TextStyle(fontWeight = FontWeight.W200),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
fun RecipeDescription(description: String) {
    Text(
        text = description,
        style = TextStyle(fontWeight = FontWeight.W400, color = Color.Gray),
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
fun RecipePreparationTime(time: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Default.Timer,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = time,
            style = TextStyle(fontWeight = FontWeight.W400, color = Color.Gray),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
fun FavoriteIcon(
    align: Modifier,
    favorite: Boolean,
    listRecipesViewModel: ListRecipesViewModel,
    id: Number,
    page: String
) {
    Icon(
        imageVector = Icons.Default.Favorite,
        contentDescription = null,
        modifier = align
            .clickable {
                if (!favorite) {
                    listRecipesViewModel.addFavorite(id)
                }
                else
                    listRecipesViewModel.removeFavorite(id, page)
            },
        tint = if (favorite) Color.Green else Color.Gray
    )
}