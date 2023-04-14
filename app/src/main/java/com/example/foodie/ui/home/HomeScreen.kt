package com.example.foodie.ui.home

import AddItemButton
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.foodie.navigation.ItemsNav
import com.example.foodie.ui.recipe.RecipeScreen
import com.example.foodie.ui.recipe.RecipeViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(recipeViewModel: RecipeViewModel) {
    val navController = rememberNavController()
    val scrollState = rememberScrollState()
    val showAddItemButton = remember { mutableStateOf(true) }

    LaunchedEffect(navController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            showAddItemButton.value = destination.route != "recipe"
        }
    }
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
            composable("recipe") {
                RecipeScreen(
                    viewModel = recipeViewModel,
                    navigationController = navController
                )
            }
        }
    }
    if (showAddItemButton.value && scrollState.value <= 0) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 56.dp),
            contentAlignment = Alignment.BottomEnd) {
            AddItemButton(itemName = "", onClick = {
                navController.navigate("recipe") {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                }
            })
        }
    }
}
