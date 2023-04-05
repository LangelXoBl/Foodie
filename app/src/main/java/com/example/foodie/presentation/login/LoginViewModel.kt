package com.example.foodie.presentation.login

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodie.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {
    //val loginUseCase = LoginUseCase()

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _valid = MutableLiveData<Boolean>()
    val valid: LiveData<Boolean> = _valid

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _auth = MutableLiveData<Boolean>()
    val auth: LiveData<Boolean> = _auth

    private val _showPassword = MutableLiveData<Boolean>()
    val showPassword : LiveData<Boolean> = _showPassword

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _valid.value = isValidEmail(email) && isValidPassword(password)
    }

    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isValidPassword(password: String): Boolean = password.length > 6
    fun onLoginSelected() {
        viewModelScope.launch {
            _isLoading.value = true
            val result = loginUseCase(email.value!!, password.value!!)
            if(result) Log.i("Login", "Ok")
            else Log.i("login", "mal")
            _isLoading.value= false
        }
    }
}