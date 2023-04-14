package com.example.foodie.data.remote

import android.util.Log
import com.example.foodie.data.model.LoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginService @Inject constructor(private val loginDataSource: LoginDataSource) {
    //private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun login(user: String, password:String): String{
        return withContext(Dispatchers.IO){
            val request = LoginRequest(user, password)
            val response = loginDataSource.login(request)
            Log.i("token","${response.body()?.Token}")
            response.body()?.Token ?: ""
        }
    }
}