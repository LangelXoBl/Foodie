package com.example.foodie.ui.onboarding

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodie.utils.PreferenceHelper
import com.example.foodie.utils.PreferenceHelper.set
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class OnboardViewModel @Inject constructor(@ApplicationContext private val context: Context) :
    ViewModel() {
    private val _newUser = MutableLiveData<Boolean>()
    val newUser: LiveData<Boolean> = _newUser
    private val preferences = PreferenceHelper.defaultPrefs(context)

    fun finishOnboard() {
        preferences["newUser"] = false
        _newUser.value = false

    }
}