package com.example.foodie.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("token")
    @Expose
    var Token: String=""
        )