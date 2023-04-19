package com.example.foodie.domain.recipe

import com.example.foodie.data.model.favorite.FavoriteList
import com.example.foodie.data.model.favorite.FavoriteResponse
import com.example.foodie.data.model.recipe.RecipeResponse
import com.example.foodie.data.repository.RecipeRepository
import javax.inject.Inject

class RecipeUserCase @Inject constructor(private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke(): List<RecipeResponse> ?{
        return recipeRepository.getRecipes()
    }

    suspend fun getDetailRecipe(id: Number):RecipeResponse? {
        return recipeRepository.getDetailRecipes(id)
    }
    suspend fun addFavorite(id: Number):FavoriteResponse? {
        return recipeRepository.addFavorite(id)
    }

    suspend fun removeFavorite(id: Number):FavoriteResponse? {
        return recipeRepository.removeFavorite(id)
    }

    suspend fun favoritesList():List<FavoriteList>? {
        return recipeRepository.favoriteList()
    }
}