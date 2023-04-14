package com.example.foodie.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adb
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ItemsNav(
    val icon: ImageVector,
    val title: String,
    val rute: String
){
    object Screen1: ItemsNav(Icons.Default.Home, "Home", "home")
    object Screen2: ItemsNav(Icons.Default.Settings, "Settings", "settings")

    object Screen3: ItemsNav(Icons.Default.Adb, "Recipe", "recipe")
}
