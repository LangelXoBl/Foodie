package com.example.foodie.data.remote.recipe


import android.content.Context
import android.util.Log
import com.example.foodie.data.model.categories.CategoriesResponse
import com.example.foodie.data.model.categories.FavoriteRequest
import com.example.foodie.data.model.favorite.FavoriteList
import com.example.foodie.data.model.favorite.FavoriteResponse
import com.example.foodie.data.model.ingredient.IngredientResponse
import com.example.foodie.data.model.recipe.RecipeRequest
import com.example.foodie.data.model.recipe.RecipeResponse
import com.example.foodie.utils.PreferenceHelper
import com.example.foodie.utils.PreferenceHelper.get
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipeService @Inject constructor(@ApplicationContext private val context: Context, private val recipeDataSource: RecipeDataSource) {

    private val preferences = PreferenceHelper.defaultPrefs(context)

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
        val token = preferences["token",""]
        return withContext(Dispatchers.IO) {
            Log.i("Token get", token)
            val response = recipeDataSource.recipes("Token $token")
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
        val token = preferences["token",""]
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
                ), token
            )
            if (response.isSuccessful) {
                Log.i("Recipes", "Recipe created")
            } else {
                Log.i("Recipes", "too mal")
                throw Exception("Error en la solicitud: ${response.errorBody()?.string()}")
            }
        }
    }

    suspend fun addFavorite(id: Number): FavoriteResponse? {
        val token = preferences["token",""]
        return withContext(Dispatchers.IO) {
            val response = recipeDataSource.addFavorite("Token $token", FavoriteRequest(id))
            if (response.isSuccessful) {
                response.body()
            } else {
                Log.i("addFav", "too mal")
                throw Exception("Error en la solicitud: ${response.errorBody()?.string()}")
            }
        }
    }

    suspend fun removeFavorite(id: Number): FavoriteResponse? {
        val token = preferences["token",""]
        return withContext(Dispatchers.IO) {
            val response = recipeDataSource.removeFavorite("Token $token", id)
            if (response.isSuccessful) {
                response.body()
            } else {
                Log.i("removeFav", "too mal")
                throw Exception("Error en la solicitud: ${response.errorBody()?.string()}")
            }
        }
    }

    suspend fun favoriteList(): List<FavoriteList>? {
        val token = preferences["token",""]
        return withContext(Dispatchers.IO) {
            val response = recipeDataSource.favoriteList("Token $token")
            if (response.isSuccessful) {
                response.body()
            } else {
                Log.i("list", "too mal")
                throw Exception("Error en la solicitud: ${response.errorBody()?.string()}")
            }
        }
    }
}
