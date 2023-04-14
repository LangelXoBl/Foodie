package com.example.foodie.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.foodie.ui.home.HomeScreen
import com.example.foodie.ui.login.LoginViewModel
import com.example.foodie.ui.theme.FoodieTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.compose.rememberNavController
import com.example.foodie.ui.login.LoginScreen
import com.example.foodie.ui.onboarding.Onboard
import com.example.foodie.ui.recipe.RecipeViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private val recipeViewModel: RecipeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodieTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navigationController= rememberNavController()
                    NavHost(navController = navigationController, startDestination = "login"){
                        composable("onboard"){ Onboard()}
                        composable("login"){ LoginScreen(viewModel = loginViewModel, navigationController = navigationController)}
                        composable("home"){ HomeScreen(recipeViewModel = recipeViewModel) }
                    }
                }
            }
        }
    }
}
