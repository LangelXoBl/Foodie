package com.example.foodie.ui.home

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
import com.example.foodie.data.model.recipe.RecipeResponse
import com.example.foodie.ui.components.recipe.CardRecipe

//@Preview
@Composable
fun ListRecipes(listRecipesViewModel: ListRecipesViewModel, navController: NavController){

    //val listRecipesViewModel: ListRecipesViewModel = viewModel()
    
    val listRecipes: List<RecipeResponse> by listRecipesViewModel.recipeList.observeAsState(initial = emptyList())
    
    LaunchedEffect(true ){
        listRecipesViewModel.getRecipes()
    }
    if(listRecipes.isEmpty()) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }
    else {
        LazyColumn() {

            items(count = listRecipes.size) { index ->
                val recipe = listRecipes[index]
                CardRecipe(recipe, navController)
            }
            item {
                Spacer(Modifier.height(50.dp))}
        }
    }
}