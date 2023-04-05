package com.example.foodie.domain

import com.example.foodie.data.repository.LoginRepository

class LoginUseCase {
    private val respository = LoginRepository()

    suspend operator fun invoke(user: String, password: String): Boolean{
        return respository.login(user, password)=="success"
    }
}