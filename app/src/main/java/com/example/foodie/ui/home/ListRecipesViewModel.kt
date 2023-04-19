package com.example.foodie.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodie.data.model.favorite.FavoriteList
import com.example.foodie.data.model.recipe.RecipeResponse
import com.example.foodie.domain.recipe.RecipeUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListRecipesViewModel @Inject constructor(private val recipeUserCase: RecipeUserCase) :
    ViewModel() {
    private val _recipeList = MutableLiveData<List<RecipeResponse>>()
    val recipeList: LiveData<List<RecipeResponse>> = _recipeList

    private val _favoriteList = MutableLiveData<List<FavoriteList>>()
    val favoriteList: LiveData<List<FavoriteList>> = _favoriteList

    fun getFavorites() {
        viewModelScope.launch {
            try {
                _favoriteList.value = recipeUserCase.favoritesList()
            } catch (e: Exception) {
                Log.i("error", "${e.message}")
            }
        }
    }

    fun getRecipes() {
        viewModelScope.launch {
            try {
                _recipeList.value = recipeUserCase.invoke()
            } catch (e: Exception) {
                Log.i("error", "${e.message}")
            }
        }
    }

    fun addFavorite(id: Number) {
        viewModelScope.launch {
            try {
                recipeUserCase.addFavorite(id)
                val updatedList = _recipeList.value?.map { recipe ->
                    if (recipe.id == id) {
                        recipe.copy(favorite = true) // Actualizar la propiedad "favorite"
                    } else {
                        recipe
                    }
                }
                _recipeList.value = updatedList
            } catch (e: Exception) {
                Log.i("error", "${e.message}")
            }
        }
    }

    fun removeFavorite(id: Number, page: String) {
        viewModelScope.launch {
            try {
                recipeUserCase.removeFavorite(id)
                if (page == "favorites") {
                    val updatedList = _favoriteList.value?.filter { favorite ->
                        favorite.recipe.id != id
                    }
                    _favoriteList.value = updatedList
                } else {
                    val updatedList = _recipeList.value?.map { recipe ->
                        if (recipe.id == id) {
                            recipe.copy(favorite = false)
                        } else {
                            recipe
                        }
                    }
                    _recipeList.value = updatedList
                }
            } catch (e: Exception) {
                Log.i("error", "${e.message}")
            }
        }
    }
}