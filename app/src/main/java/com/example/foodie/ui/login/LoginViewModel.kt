package com.example.foodie.ui.login

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.foodie.domain.LoginUseCase
import com.example.foodie.navigation.ItemsNav
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _valid = MutableLiveData<Boolean>()
    val valid: LiveData<Boolean> = _valid

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _auth = MutableLiveData<Boolean>()
    val auth: LiveData<Boolean> = _auth


    fun onLoginChanged(email: String, password: String) {
        _username.value = email
        _password.value = password
        _valid.value = isValidPassword(password)
    }

    private fun isValidPassword(password: String): Boolean = password.length > 6

    fun onLoginSelected() {
        viewModelScope.launch {
            _isLoading.value = true
            val result = loginUseCase(username.value!!, password.value!!)
            if (result) {
                Log.i("Login", "Ok")
                _auth.value = true
            } else {
                Log.i("login", "mal")
                _auth.value = false
            }
            _isLoading.value = false
        }
    }

    fun anonymous() {
        Log.i("Invited", "logIn")
        _auth.value = true
    }
}