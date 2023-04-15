package com.example.foodie.data.remote.recipe

import com.example.foodie.data.model.recipe.RecipeResponse
import dagger.hilt.android.HiltAndroidApp
import retrofit2.Response
import retrofit2.http.*
import javax.inject.Inject

interface RecipeDataSource {
    @GET("foodie-finder/")
    suspend fun finder(
        @Header("Authorization") token: String,
        @Query("search") ingredient: String
    ): Response<RecipeResponse>
}
