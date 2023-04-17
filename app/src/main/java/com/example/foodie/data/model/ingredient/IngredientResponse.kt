package com.example.foodie.data.model.ingredient

data class IngredientResponse(
    val name: String,
    var url: String? = null,
    var status: Status? = null
){
    enum class Status {
        LOADING,
        SUCCESS,
        ERROR
    }
}
