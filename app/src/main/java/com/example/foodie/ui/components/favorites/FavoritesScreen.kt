package com.example.foodie.ui.components.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodie.data.model.favorite.FavoriteList
import com.example.foodie.ui.components.recipe.CardRecipe
import com.example.foodie.ui.home.ListRecipesViewModel

@Composable
fun FavoritesScreen(listRecipesViewModel: ListRecipesViewModel, navController: NavController) {

    val listRecipes: List<FavoriteList> by listRecipesViewModel.favoriteList.observeAsState(initial = emptyList())

    LaunchedEffect(true){
        listRecipesViewModel.getFavorites()
    }

    if(listRecipes.isEmpty()) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }else {
        LazyColumn(modifier = Modifier
            .background(color = Color(0xFF141414))
            .fillMaxSize()) {

            items(count = listRecipes.size) { index ->
                val recipe = listRecipes[index]
                CardRecipe(recipe.recipe, navController, listRecipesViewModel, "favorites")
            }
            item {
                Spacer(Modifier.height(50.dp))
            }
        }
    }
}