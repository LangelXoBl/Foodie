package com.example.foodie.ui.detail

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
class DetailViewModel @Inject constructor(private val userCase: RecipeUserCase) : ViewModel() {
    private val _detail = MutableLiveData<RecipeResponse>()
    val detail: LiveData<RecipeResponse> = _detail

    fun getDetail(id: Number) {
        _detail.value=null
        viewModelScope.launch {
            try {
                _detail.value = userCase.getDetailRecipe(id)
            } catch (e: Exception) {
                Log.i("error", "${e.message}")
            }
        }
    }
}