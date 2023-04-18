package com.example.foodie.data.remote.recipe


import android.util.Log
import com.example.foodie.data.model.LoginRequest
import com.example.foodie.data.model.categories.CategoriesResponse
import com.example.foodie.data.model.ingredient.IngredientResponse
import com.example.foodie.data.model.recipe.RecipeRequest
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

    suspend fun getRecipes(): List<RecipeResponse>? {
        return withContext(Dispatchers.IO) {
            val response = recipeDataSource.recipes()
            Log.i("Recipes", "${response.body()?.size}")
            if (response.isSuccessful) {
                response.body()
            } else {
                Log.i("Recipes", "too mal")
                throw Exception("Error en la solicitud: ${response.errorBody()?.string()}")
            }
        }
    }

    suspend fun getDetailRecipe(id: Number):RecipeResponse?{
        return withContext(Dispatchers.IO){
            val response = recipeDataSource.recipeDetail(id)
            if(response.isSuccessful) {
                response.body()
            }
            else{
                throw Exception("Error al obtener detalles: ${response.errorBody()?.string()}")
            }
        }
    }

    suspend fun getCategories(): List<CategoriesResponse>? {
        return withContext(Dispatchers.IO) {
            val response = recipeDataSource.allCategories()
            if (response.isSuccessful) {
                response.body()
            } else {
                throw Exception("Error en la solicitud: ${response.errorBody()?.string()}")
            }
        }
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
        return withContext(Dispatchers.IO) {
            val response = recipeDataSource.createRecipe(
                RecipeRequest(
                    title = title,
                    description = description,
                    image = image,
                    preparation_time = preparation_time,
                    ingredients = ingredients,
                    instructions = instructions,
                    categories = categories.map(CategoriesResponse::id)
                )
            )
            if (response.isSuccessful) {
                Log.i("Recipes", "Recipe created")
            } else {
                Log.i("Recipes", "too mal")
                throw Exception("Error en la solicitud: ${response.errorBody()?.string()}")
            }
        }
    }
}
