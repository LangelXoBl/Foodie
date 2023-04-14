package com.example.foodie.data.model.recipe

data class RecipeResponse(
    val name: String,
    var url: String? = null,
    var status: Status? = null
) {
    enum class Status {
        LOADING,
        SUCCESS,
        ERROR
    }
}
