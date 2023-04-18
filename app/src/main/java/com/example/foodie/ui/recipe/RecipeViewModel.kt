package com.example.foodie.ui.recipe

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodie.data.model.categories.CategoriesResponse
import com.example.foodie.data.model.ingredient.IngredientResponse
import com.example.foodie.data.model.recipe.RecipeRequest
import com.example.foodie.domain.FinderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(private val finderUseCase: FinderUseCase) : ViewModel() {
    private var _ingredientList = mutableStateOf(emptyList<IngredientResponse>())
    val ingredientList: List<IngredientResponse> get() = _ingredientList.value

    private var _categories = mutableStateOf(emptyList<CategoriesResponse>())
    val categories: List<CategoriesResponse> get() = _categories.value

    private var _selectedCategories = mutableStateOf(emptyList<CategoriesResponse>())
    val selectedCategories: List<CategoriesResponse> get() = _selectedCategories.value

    private var _selectedCategory = CategoriesResponse(0, "")
    val selectedCategory: CategoriesResponse get() = _selectedCategory

    private var _instructionsList = mutableStateOf(emptyList<String>())
    val instructionsList: List<String> get() = _instructionsList.value

    fun getCategories() {
        viewModelScope.launch {
            _categories.value = finderUseCase.getCategories() ?: emptyList()
        }
    }

    fun createRecipe(
        title: String,
        description: String,
        image: String,
        preparation_time: String,
        ingredients: List<IngredientResponse> = ingredientList,
        instructions: List<String> = instructionsList,
        categories: List<CategoriesResponse> = selectedCategories,

        ) {
        try {
            viewModelScope.launch {
                val response = finderUseCase.createRecipe(
                    title,
                    description,
                    image,
                    preparation_time,
                    ingredients,
                    instructions,
                    categories
                )
                RecipeRequest.Status.SUCCESS
            }
        } catch (e: Exception) {
            e.printStackTrace()
            RecipeRequest.Status.ERROR
        }

    }

    fun addIngredient(name: String) {
        val ingredient = IngredientResponse(name, status = IngredientResponse.Status.LOADING)
        _ingredientList.value = _ingredientList.value + ingredient

        viewModelScope.launch {
            try {
                val url = finderUseCase("Token 77ee454e092260498a59f6e279a42cf6bcb9571d", name)
                _ingredientList.value = _ingredientList.value.map { currentIngredient ->
                    if (currentIngredient.name == name) {
                        currentIngredient.copy(
                            url = url,
                            status = IngredientResponse.Status.SUCCESS
                        )
                    } else {
                        currentIngredient
                    }
                }
            } catch (e: Exception) {
                _ingredientList.value = _ingredientList.value.map { currentIngredient ->
                    if (currentIngredient.name == name) {
                        currentIngredient.copy(status = IngredientResponse.Status.ERROR)
                    } else {
                        currentIngredient
                    }
                }
            }
        }
    }

    fun retryGetIngredientUrl(ingredient: IngredientResponse) {
        _ingredientList.value = _ingredientList.value.map { currentIngredient ->
            if (currentIngredient.name == ingredient.name) {
                currentIngredient.copy(status = IngredientResponse.Status.LOADING)
            } else {
                currentIngredient
            }
        }
        viewModelScope.launch {
            try {
                val link =
                    finderUseCase("Token 77ee454e092260498a59f6e279a42cf6bcb9571d", ingredient.name)
                _ingredientList.value = _ingredientList.value.map { currentIngredient ->
                    if (currentIngredient.name == ingredient.name) {
                        currentIngredient.copy(
                            url = link,
                            status = IngredientResponse.Status.SUCCESS
                        )
                    } else {
                        currentIngredient
                    }
                }
            } catch (e: Exception) {
                _ingredientList.value = _ingredientList.value.map { currentIngredient ->
                    if (currentIngredient.name == ingredient.name) {
                        currentIngredient.copy(status = IngredientResponse.Status.ERROR)
                    } else {
                        currentIngredient
                    }
                }
            }
        }
    }

    fun removeIngredient(ingredient: IngredientResponse) {
        _ingredientList.value = _ingredientList.value.filter { it.name != ingredient.name }
    }

    fun addInstruction(instruction: String) {
        val nextIndex = _instructionsList.value.size + 1
        _instructionsList.value = _instructionsList.value + "${nextIndex}.- $instruction"
    }

    fun removeInstruction(instruction: String) {
        _instructionsList.value = _instructionsList.value.filter { it != instruction }
    }

    fun addCategory() {
        _selectedCategories.value = _selectedCategories.value + CategoriesResponse(
            selectedCategory.id,
            selectedCategory.name
        )
        _selectedCategory = CategoriesResponse(0, "")
    }


    fun removeCategory(category: String) {
        _selectedCategories.value = _selectedCategories.value.filter { it.name != category }
    }

}
