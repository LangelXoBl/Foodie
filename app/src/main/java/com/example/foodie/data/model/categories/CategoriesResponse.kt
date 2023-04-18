package com.example.foodie.data.model.categories

import com.google.gson.annotations.SerializedName

class CategoriesResponse(
    @SerializedName("id")
    var id: Number = 0,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("description")
    var description: String = "",

    ){
enum class Status {
    LOADING,
    SUCCESS,
    ERROR
}
}