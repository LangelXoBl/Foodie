package com.example.foodie.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodie.data.model.recipe.RecipeResponse
import com.example.foodie.domain.recipe.RecipeUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListRecipesViewModel @Inject constructor(private val recipeUserCase: RecipeUserCase): ViewModel(){
    private val _recipeList= MutableLiveData<List<RecipeResponse>>()
    val recipeList: LiveData<List<RecipeResponse>> = _recipeList

    fun getRecipes (){
        viewModelScope.launch {
            try {
                _recipeList.value = recipeUserCase.invoke()
            }catch (e: Exception){
                Log.i("error","${e.message}")
            }
        }
    }
}