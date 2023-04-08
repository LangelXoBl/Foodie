package com.example.foodie.domain

import com.example.foodie.data.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: LoginRepository) {
    //private val respository = LoginRepository()

    suspend operator fun invoke(user: String, password: String): Boolean{
        return repository.login(user, password)!=""
    }
}