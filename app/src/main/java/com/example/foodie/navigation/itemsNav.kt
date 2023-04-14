package com.example.foodie.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adb
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ItemsNav(
    val icon: ImageVector,
    val title: String,
    val route: String
) {
    object HomeRoute : ItemsNav(Icons.Default.Home, "Home", "home")
    object SettingsRoute : ItemsNav(Icons.Default.Settings, "Settings", "settings")
    object FavoriteRoute : ItemsNav(Icons.Default.Favorite, "Favorites", "favorites")
    object Screen3: ItemsNav(Icons.Default.Adb, "Recipe", "recipe")
}
