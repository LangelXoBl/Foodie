package com.example.foodie.ui.home

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.foodie.navigation.ItemsNav
import com.example.foodie.ui.onboarding.Onboard

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = navController.currentBackStackEntryAsState().value?.destination?.route
                            ?: ""
                    )
                }
            )
        },
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                BottomNavigationItem(
                    icon = { Icon(ItemsNav.HomeRoute.icon, contentDescription = null) },
                    label = { Text(text = ItemsNav.HomeRoute.title) },
                    selected = currentRoute == ItemsNav.HomeRoute.route,
                    onClick = {
                        navController.popBackStack(navController.graph.startDestinationId, false)
                    }
                )
                BottomNavigationItem(
                    icon = { Icon(ItemsNav.FavoriteRoute.icon, contentDescription = null) },
                    label = { Text(ItemsNav.FavoriteRoute.title) },
                    selected = currentRoute == ItemsNav.FavoriteRoute.route,
                    onClick = {
                        navController.navigate(ItemsNav.FavoriteRoute.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                        }
                    }
                )
                BottomNavigationItem(
                    icon = { Icon(ItemsNav.SettingsRoute.icon, contentDescription = null) },
                    label = { Text(ItemsNav.SettingsRoute.title) },
                    selected = currentRoute == ItemsNav.SettingsRoute.route,
                    onClick = {
                        navController.navigate(ItemsNav.SettingsRoute.route) {
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
        NavHost(navController = navController, startDestination = ItemsNav.HomeRoute.route) {
            composable(ItemsNav.HomeRoute.route) { Text(text = "home") }
            composable(ItemsNav.SettingsRoute.route) { Text(text = "settings") }
            composable(ItemsNav.FavoriteRoute.route) { Text(text = "fav") }
        }
    }
}
