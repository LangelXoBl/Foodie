package com.example.foodie.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.example.foodie.ui.home.HomeScreen
import com.example.foodie.ui.login.LoginScreen
import com.example.foodie.ui.login.LoginViewModel
import com.example.foodie.ui.onboarding.Onboard
import com.example.foodie.ui.onboarding.OnboardViewModel
import com.example.foodie.ui.recipe.RecipeViewModel
import com.example.foodie.ui.theme.FoodieTheme
import com.example.foodie.utils.PreferenceHelper
import com.example.foodie.utils.PreferenceHelper.get
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private val onboardViewModel: OnboardViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodieTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val auth: Boolean by loginViewModel.auth.observeAsState(initial = false)
                    val newUser: Boolean by onboardViewModel.newUser.observeAsState(initial = true)
                    val preferences = PreferenceHelper.defaultPrefs(this)
                    if (auth || preferences["token", ""].isNotBlank()) {
                        HomeScreen()
                    } else
                        if (newUser && preferences["newUser", true])
                            Onboard(onboardViewModel)
                        else
                            LoginScreen(viewModel = loginViewModel)

                }
            }
        }
    }
}
