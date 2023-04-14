package com.example.foodie.data.remote

import com.example.foodie.data.model.LoginRequest
import com.example.foodie.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginDataSource {
    @POST("user/login/")
    suspend fun login(@Body request:LoginRequest): Response<LoginResponse>
}