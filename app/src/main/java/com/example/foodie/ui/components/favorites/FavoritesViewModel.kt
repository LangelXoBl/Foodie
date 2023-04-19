package com.example.foodie.ui.components.favorites

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodie.data.model.favorite.FavoriteList
import com.example.foodie.domain.recipe.RecipeUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val recipeUserCase: RecipeUserCase): ViewModel() {
    private val _favoriteList= MutableLiveData<List<FavoriteList>>()
    val favoriteList: LiveData<List<FavoriteList>> = _favoriteList

    fun getFavorites (){
        viewModelScope.launch {
            try {
                _favoriteList.value = recipeUserCase.favoritesList()
            }catch (e: Exception){
                Log.i("error","${e.message}")
            }
        }
    }

    fun addFavorite(id:Number){
        viewModelScope.launch {
            try {
                recipeUserCase.addFavorite(id)
                val updatedList = _favoriteList.value?.map { favorite ->
                    if (favorite.id == id) {
                        val updatedRecipe = favorite.recipe.copy(favorite = true)
                        favorite.copy(recipe = updatedRecipe) // Actualizar la propiedad "favorite"
                    } else {
                        favorite
                    }
                }
                _favoriteList.value = updatedList
            }catch (e: Exception){
                Log.i("error","${e.message}")
            }
        }
    }
    fun removeFavorite(id:Number){
        viewModelScope.launch {
            try {
                recipeUserCase.removeFavorite(id)
                val updatedList = _favoriteList.value?.map { favorite ->
                    if (favorite.id == id) {
                        val updatedRecipe = favorite.recipe.copy(favorite = true)
                        favorite.copy(recipe = updatedRecipe)// Actualizar la propiedad "favorite"
                    } else {
                        favorite
                    }
                }
                _favoriteList.value = updatedList
            }catch (e: Exception){
                Log.i("error","${e.message}")
            }
        }
    }
}