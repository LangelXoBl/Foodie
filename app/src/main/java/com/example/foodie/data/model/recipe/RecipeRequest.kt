package com.example.foodie.data.model.recipe

import com.example.foodie.data.model.categories.CategoriesResponse
import com.example.foodie.data.model.ingredient.IngredientResponse

class RecipeRequest (
    val title: String,
    val description: String,
    val image: String,
    val preparation_time: String,
    val ingredients: List<IngredientResponse>,
    val instructions: List<String>,
    val categories: List<Number>
        ) {
    enum class Status {
        LOADING,
        SUCCESS,
        ERROR
    }
}