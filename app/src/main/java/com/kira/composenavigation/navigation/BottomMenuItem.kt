package com.kira.composenavigation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomMenuItem(val label: String, val icon: ImageVector, val screenRoute: String) {
    object Home : BottomMenuItem("Home", Icons.Filled.Home, "home")
    object Account : BottomMenuItem("Account", Icons.Filled.Person, "account")
    object Mail : BottomMenuItem("Mail", Icons.Filled.Email, "mail")
    object Shop : BottomMenuItem("Shop", Icons.Filled.List, "shop")
}