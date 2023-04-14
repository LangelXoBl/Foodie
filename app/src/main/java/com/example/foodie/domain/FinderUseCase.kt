package com.example.foodie.domain

import com.example.foodie.data.repository.RecipeRepository
import javax.inject.Inject

class FinderUseCase @Inject constructor(private val repository: RecipeRepository) {
    suspend operator fun invoke(token: String, ingredient: String): String {
        val recipeResponse = repository.getRecipeIngredient(token, ingredient)
        return recipeResponse.url ?: ""
    }
}

