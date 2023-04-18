package com.example.foodie.data.remote.recipe

import com.example.foodie.data.model.ingredient.IngredientResponse
import com.example.foodie.data.model.recipe.RecipeResponse
import retrofit2.Response
import retrofit2.http.*

interface RecipeDataSource {
    @GET("foodie-finder/")
    suspend fun finder(
        @Header("Authorization") token: String,
        @Query("search") ingredient: String
    ): Response<IngredientResponse>

    @GET("recipe/")
    suspend fun recipes():Response<List<RecipeResponse>>

    @GET("recipe/{id}")
    suspend fun recipeDetail(@Path("id") id: Number): Response<RecipeResponse>
}
