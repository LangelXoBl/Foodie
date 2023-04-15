package com.example.foodie.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.foodie.ui.components.recipe.CardRecipe

//@Preview
@Composable
fun ListRecipes(){
    LazyColumn {
        items(count = 5, key = null){
            CardRecipe()
        }
    }
}