package com.example.foodie.domain

import com.example.foodie.data.model.categories.CategoriesResponse
import com.example.foodie.data.model.ingredient.IngredientResponse
import com.example.foodie.data.repository.RecipeRepository
import javax.inject.Inject

class FinderUseCase @Inject constructor(private val repository: RecipeRepository) {
    suspend operator fun invoke(token: String, ingredient: String): String {
        val recipeResponse = repository.getRecipeIngredient(token, ingredient)
        return recipeResponse.url ?: ""
    }
    suspend fun getCategories(): List<CategoriesResponse>? {
        return repository.getCategories()
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
        return repository.createRecipe(title, description, image,preparation_time, ingredients, instructions, categories)
    }
}

