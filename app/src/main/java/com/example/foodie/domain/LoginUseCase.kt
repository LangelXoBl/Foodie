package com.example.foodie.domain

import android.content.Context
import com.example.foodie.data.repository.LoginRepository
import com.example.foodie.utils.PreferenceHelper
import com.example.foodie.utils.PreferenceHelper.set
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: LoginRepository
) {
    //private val respository = LoginRepository()
    private val preferences = PreferenceHelper.defaultPrefs(context)

    suspend operator fun invoke(user: String, password: String): Boolean {
        val token = repository.login(user, password)
        if (token.isNotBlank()) preferences["token"] = token
        return token.isNotBlank()
    }
}