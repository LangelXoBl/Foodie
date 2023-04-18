package com.example.foodie.data.model.ingredient

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class IngredientResponse(
    @SerializedName("description")
    @Expose
    val name: String="",
    var url: String? = null,
    var status: Status? = null
){
    enum class Status {
        LOADING,
        SUCCESS,
        ERROR
    }
}
