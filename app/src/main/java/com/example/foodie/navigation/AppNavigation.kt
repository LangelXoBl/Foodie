package com.example.foodie.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodie.ui.home.HomeScreen
import com.example.foodie.ui.login.LoginScreen
import com.example.foodie.ui.login.LoginViewModel
import com.example.foodie.ui.onboarding.Onboard

@Composable
fun AppNavigation(viewModel: LoginViewModel){
    var navController = rememberNavController()


}