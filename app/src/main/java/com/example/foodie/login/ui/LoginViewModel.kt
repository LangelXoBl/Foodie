package com.example.foodie.login.ui

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel(){
    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    private val _valid = MutableLiveData<Boolean>()
    val valid : LiveData<Boolean> = _valid

    fun onLoginChanged(email:String, password: String ){
        _email.value = email
        _password.value= password
        _valid.value = isValidEmail(email) && isValidPassword(password)
    }

    private fun isValidEmail(email: String):Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    private fun isValidPassword(password: String):Boolean = password.length>6
    fun onLoginSelected() {

    }
}