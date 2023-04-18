package com.example.foodie.data.repository

import com.example.foodie.data.model.categories.CategoriesResponse
import com.example.foodie.data.model.ingredient.IngredientResponse
import com.example.foodie.data.model.recipe.RecipeResponse
import com.example.foodie.data.remote.recipe.RecipeDataSource
import com.example.foodie.data.remote.recipe.RecipeService
import javax.inject.Inject

class RecipeRepository @Inject constructor(private val api: RecipeService) {

    suspend fun getRecipeIngredient(token: String, ingredient: String): IngredientResponse {
        val url = api.getRecipeIngredient(token, ingredient)
        return IngredientResponse(ingredient, url, IngredientResponse.Status.SUCCESS)
    }

    suspend fun getRecipes(): List<RecipeResponse>? {
        return api.getRecipes()
    }

    suspend fun getDetailRecipes(id: Number):RecipeResponse?{
        return api.getDetailRecipe(id)
    }

    suspend fun getCategories(): List<CategoriesResponse>? {
        return api.getCategories()
    }

    suspend fun createRecipe(
        title: String,
        description: String,
        image: String,
        preparation_time: String,
        ingredients: List<IngredientResponse>,
        instructions: List<String>,
        categories: List<CategoriesResponse>
    ) {
        return api.createRecipe(title, description, image,preparation_time, ingredients, instructions, categories)
    }
}

