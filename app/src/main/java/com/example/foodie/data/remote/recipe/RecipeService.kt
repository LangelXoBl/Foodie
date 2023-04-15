package com.example.foodie.data.remote.recipe


import android.util.Log
import com.example.foodie.data.model.LoginRequest
import com.example.foodie.data.model.recipe.RecipeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipeService @Inject constructor(private val recipeDataSource: RecipeDataSource) {


    suspend fun getRecipeIngredient(token: String, ingredient: String): String {
        return withContext(Dispatchers.IO) {
            val response = recipeDataSource.finder(token, ingredient)
            if (response.isSuccessful) {
                Log.i("url", "${response.body()?.url}")
                response.body()?.url ?: ""
            } else {
                throw Exception("Error en la solicitud: ${response.errorBody()?.string()}")
            }
        }
    }
}
