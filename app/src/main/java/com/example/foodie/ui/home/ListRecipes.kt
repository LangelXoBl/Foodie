package com.example.foodie.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.foodie.data.model.recipe.RecipeResponse
import com.example.foodie.ui.components.recipe.CardRecipe

//@Preview
@Composable
fun ListRecipes(listRecipesViewModel: ListRecipesViewModel){

    //val listRecipesViewModel: ListRecipesViewModel = viewModel()
    
    val listRecipes: List<RecipeResponse> by listRecipesViewModel.recipeList.observeAsState(initial = emptyList())
    
    LaunchedEffect(true ){
        listRecipesViewModel.getRecipes()
    }
    LazyColumn {
        items(count = listRecipes.size){index->
            val recipe = listRecipes[index]
            CardRecipe(recipe)
        }
    }
}