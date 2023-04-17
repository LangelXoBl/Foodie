package com.example.foodie.data.model.recipe

import com.example.foodie.data.model.ingredient.IngredientResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @SerializedName("id")
    @Expose
    var id: Number = 0,
    @SerializedName("categories")
    @Expose
    val categories: List<String>,
    @SerializedName("instructions")
    @Expose
    var instructions: List<String>,
    @SerializedName("ingredients")
    @Expose
    var ingredients: List<IngredientResponse>,
    @SerializedName("title")
    @Expose
    var title: String = "",
    @SerializedName("description")
    @Expose
    var description: String = "",
    @SerializedName("image")
    @Expose
    var image: String = "",
    @SerializedName("preparation_time")
    @Expose
    var preparation_time: String = ""


) {
    enum class Status {
        LOADING,
        SUCCESS,
        ERROR
    }
}
