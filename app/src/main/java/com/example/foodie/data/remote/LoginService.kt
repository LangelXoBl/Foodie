package com.example.foodie.data.remote

import com.example.foodie.core.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun login(user: String, password:String): String{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(LoginDataSource::class.java).login(user, password)
            response.body()?.Estatus ?: "declined"
        }
    }
}