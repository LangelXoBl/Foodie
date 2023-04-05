package com.example.foodie.data.remote

import com.example.foodie.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LoginDataSource {
    @GET("v3/a0699711-d1e0-40c2-8de6-a0df2fec905b")
    suspend fun login(@Query("user") user: String, @Query("password") password: String): Response<LoginResponse>
}