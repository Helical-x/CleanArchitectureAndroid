package com.example.ceibatest.presentation

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ceibatest.presentation.user_detail.UserDetailScreen
import com.example.ceibatest.presentation.user_list.UserListScreen


@Composable
fun Navigation() {

    val navController = rememberNavController()

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = "Prueba de Ingreso",
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Black
            )
        })
    }
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = Screen.UserListScreen.route) {
            composable(route = Screen.UserListScreen.route) {
                UserListScreen(navController)
            }
            composable(route = Screen.UserDetailScreen.route + "/{userId}") {
                UserDetailScreen(navController)
            }
        }
    }
}