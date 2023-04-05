package com.example.foodie.data.repository

import com.example.foodie.data.remote.LoginService

class LoginRepository {
    private val api = LoginService()

    suspend fun login(user:String, password: String): String{
        return api.login(user, password)
    }
}