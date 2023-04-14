package com.example.foodie.data.repository

import com.example.foodie.data.model.recipe.RecipeResponse
import com.example.foodie.data.remote.recipe.RecipeService
import javax.inject.Inject

class RecipeRepository @Inject constructor(private val api: RecipeService) {

    suspend fun getRecipeIngredient(token: String, ingredient: String): RecipeResponse {
        val url = api.getRecipeIngredient(token, ingredient)
        return RecipeResponse(ingredient, url, RecipeResponse.Status.SUCCESS)
    }
}

