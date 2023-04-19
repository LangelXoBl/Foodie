package com.example.foodie.data.remote.recipe

import com.example.foodie.data.model.categories.CategoriesResponse
import com.example.foodie.data.model.categories.FavoriteRequest
import com.example.foodie.data.model.favorite.FavoriteList
import com.example.foodie.data.model.favorite.FavoriteResponse
import com.example.foodie.data.model.ingredient.IngredientResponse
import com.example.foodie.data.model.recipe.RecipeRequest
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
    suspend fun recipes(@Header("Authorization") token: String):Response<List<RecipeResponse>>

    @POST("recipe/")
    suspend fun createRecipe(@Body recipe: RecipeRequest): Response<RecipeResponse>
    
    @GET("recipe/{id}")
    suspend fun recipeDetail(@Path("id") id: Number): Response<RecipeResponse>

    @GET("recipe/category/")
    suspend fun allCategories(): Response<List<CategoriesResponse>>

    @POST("recipe/favorites/")
    suspend fun addFavorite(@Header("Authorization") token: String, @Body body:FavoriteRequest):Response<FavoriteResponse>

    @DELETE("/recipe/favorites/{id}/")
    suspend fun removeFavorite(@Header("Authorization") token: String,@Path("id")id:Number): Response<FavoriteResponse>

    @GET("recipe/favorites/")
    suspend fun favoriteList(@Header("Authorization") token: String):Response<List<FavoriteList>>
}
