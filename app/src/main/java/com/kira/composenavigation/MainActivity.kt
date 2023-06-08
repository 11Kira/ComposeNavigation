package com.kira.composenavigation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kira.composenavigation.navigation.AccountScreen
import com.kira.composenavigation.navigation.BottomMenuItem
import com.kira.composenavigation.navigation.HomeScreen
import com.kira.composenavigation.navigation.MailScreen
import com.kira.composenavigation.navigation.ShopScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreenView()
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreenView(){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigation(navController = navController) }
    ) {

        NavigationGraph(navController = navController)
    }
}

@Composable
fun BottomNavigation(navController: NavController) {
    var selectedItem by remember {
        mutableStateOf("Home")
    }
    val items = listOf(
        BottomMenuItem.Home,
        BottomMenuItem.Account,
        BottomMenuItem.Mail,
        BottomMenuItem.Shop,
    )
    Box(modifier = Modifier.fillMaxSize()) {
        BottomNavigation(
            // place it at the bottom of the screen
            modifier = Modifier.align(alignment = Alignment.BottomCenter)
        ) {
            items.forEach {
                BottomNavigationItem(
                    selected = (selectedItem == it.label),
                    onClick = {
                        selectedItem = it.label
                        Log.e("nav click", it.label)
                        navController.navigate(it.screenRoute) {
                            navController.graph.startDestinationRoute?.let { screenRoute ->
                                popUpTo(screenRoute) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    label = {
                        Text(
                            text = it.label,
                            color = Color.White,
                            fontSize = 10.sp
                        ) },
                    icon = {
                        Icon(
                            imageVector  = it.icon,
                            contentDescription = it.label
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomMenuItem.Home.screenRoute) {
        composable(BottomMenuItem.Home.screenRoute) {
            HomeScreen()
        }
        composable(BottomMenuItem.Mail.screenRoute) {
            MailScreen()
        }
        composable(BottomMenuItem.Shop.screenRoute) {
            ShopScreen()
        }
        composable(BottomMenuItem.Account.screenRoute) {
            AccountScreen()
        }
    }
}