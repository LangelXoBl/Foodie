package com.example.foodie.presentation.home

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    label = { Text(text = "Inicio") },
                    selected = currentRoute == "home",
                    onClick = {
                        navController.popBackStack(navController.graph.startDestinationId, false)
                    }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Favorite, contentDescription = null) },
                    label = { Text("Favoritos") },
                    selected = currentRoute == "favorites",
                    onClick = {
                        navController.navigate("favorites") {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                        }
                    }
                )
                BottomNavigationItem(
                        icon = { Icon(Icons.Default.Favorite, contentDescription = null) },
                label = { Text("Favoritos") },
                selected = currentRoute == "favorites",
                onClick = {
                    navController.navigate("favorites") {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                    }
                }
                )
            }
        }
    ) {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") { /* Composable para la pantalla de inicio */ }
            composable("favorites") { /* Composable para la pantalla de favoritos */ }
        }
    }
}
