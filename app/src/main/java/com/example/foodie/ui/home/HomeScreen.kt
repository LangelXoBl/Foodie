package com.example.foodie.ui.home

import AddItemButton
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.foodie.navigation.ItemsNav
import com.example.foodie.ui.components.favorites.FavoritesScreen
import com.example.foodie.ui.detail.DetailRecipe
import com.example.foodie.ui.detail.DetailViewModel
import com.example.foodie.ui.recipe.RecipeScreen
import com.example.foodie.ui.recipe.RecipeViewModel
import com.example.foodie.utils.PreferenceHelper
import com.example.foodie.utils.PreferenceHelper.get

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    val preferences = PreferenceHelper.defaultPrefs(LocalContext.current)
    val token = preferences["token", ""]
    val navController = rememberNavController()
    val scrollState = rememberScrollState()
    val showAddItemButton = remember { mutableStateOf(true) }
    val recipeViewModel: RecipeViewModel = viewModel()
    val listRecipesViewModel: ListRecipesViewModel= viewModel()
    val detailViewModel: DetailViewModel = viewModel()

    LaunchedEffect(navController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            showAddItemButton.value = destination.route != "recipe"
        }
    }
    Scaffold(
        topBar = {
            if (navController.currentBackStackEntryAsState().value?.destination?.route != "detail/{id}") {
                TopAppBar(
                    title = {
                        Text(
                            text = navController.currentBackStackEntryAsState().value?.destination?.route
                                ?: "",
                            color = Color(0xFFffffff),
                        )
                    },
                    backgroundColor = Color(0xFF40454F)
                )
            }
        },
        bottomBar = {
            BottomNavigation (backgroundColor = Color(0xFF40454F)){
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                BottomNavigationItem(
                    icon = { Icon(ItemsNav.HomeRoute.icon, contentDescription = null, tint = Color(0xFFffffff)) },
                    label = { Text(text = ItemsNav.HomeRoute.title, color = Color(0xFFffffff)) },
                    selected = currentRoute == ItemsNav.HomeRoute.route,
                    onClick = {
                        navController.popBackStack(navController.graph.startDestinationId, false)
                    }
                )
                BottomNavigationItem(
                    icon = { Icon(ItemsNav.FavoriteRoute.icon, contentDescription = null, tint = Color(0xFFffffff)) },
                    label = { Text(ItemsNav.FavoriteRoute.title, color = Color(0xFFffffff)) },
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
                    icon = { Icon(ItemsNav.SettingsRoute.icon, contentDescription = null, tint = Color(0xFFffffff)) },
                    label = { Text(ItemsNav.SettingsRoute.title, color = Color(0xFFffffff)) },
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
            composable(ItemsNav.HomeRoute.route) { ListRecipes(listRecipesViewModel, navController) }
            composable(ItemsNav.SettingsRoute.route) { Text(text = "settings") }
            composable(ItemsNav.FavoriteRoute.route) { FavoritesScreen(
                listRecipesViewModel = listRecipesViewModel,
                navController = navController
            ) }
            composable(route=ItemsNav.RecipeDetail.route+"/{id}", arguments = listOf(navArgument(name = "id"){type=
                NavType.IntType}) ){ DetailRecipe(detailViewModel = detailViewModel, id = it.arguments?.getInt("id"))}
            composable("recipe") {
                RecipeScreen(recipeViewModel = recipeViewModel)
            }
        }
    }
    if ((showAddItemButton.value && scrollState.value <= 0) && token.isNotBlank())  {
        print(token)
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

