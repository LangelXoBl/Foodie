package com.example.foodie.ui.recipe

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodie.data.model.ingredient.IngredientResponse
import com.example.foodie.data.model.recipe.RecipeResponse
import com.example.foodie.domain.FinderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(private val finderUseCase: FinderUseCase) : ViewModel() {
    private var _ingredientList = mutableStateOf(emptyList<IngredientResponse>())
    val ingredientList: List<IngredientResponse> get() = _ingredientList.value


    fun addIngredient(name: String) {
        val ingredient = IngredientResponse(name, status = IngredientResponse.Status.LOADING)
        _ingredientList.value = _ingredientList.value + ingredient

        viewModelScope.launch {
            try {
                val url = finderUseCase("Token 77ee454e092260498a59f6e279a42cf6bcb9571d", name)
                _ingredientList.value = _ingredientList.value.map { currentIngredient ->
                    if (currentIngredient.name == name) {
                        currentIngredient.copy(url = url, status = IngredientResponse.Status.SUCCESS)
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
                val link = finderUseCase("Token 77ee454e092260498a59f6e279a42cf6bcb9571d", ingredient.name)
                _ingredientList.value = _ingredientList.value.map { currentIngredient ->
                    if (currentIngredient.name == ingredient.name) {
                        currentIngredient.copy(url = link, status = IngredientResponse.Status.SUCCESS)
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

}
