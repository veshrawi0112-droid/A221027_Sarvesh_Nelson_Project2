package com.example.a221027_sarvesh_mrnelson_project1.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.a221027_sarvesh_mrnelson_project1.ui.screens.*
import com.example.a221027_sarvesh_mrnelson_project1.viewmodel.MainViewModel

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val viewModel: MainViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        // LOGIN SCREEN
        composable("login") {
            LoginScreen(navController)
        }

        // REGISTER SCREEN
        composable("register") {
            RegisterScreen(navController, viewModel)
        }

        // HOME SCREEN
        composable("home") {
            HomeScreen(navController)
        }

        // DONATION FORM SCREEN
        composable("form") {
            FoodFormScreen(navController, viewModel)
        }

        // PREVIEW SCREEN
        composable("preview") {
            FoodPreviewScreen(navController, viewModel)
        }

        // DETAILS SCREEN
        composable("details") {
            FoodDetailsScreen(navController, viewModel)
        }

        // SUMMARY SCREEN
        composable("summary") {
            SummaryScreen(navController, viewModel)
        }

        // PROFILE SCREEN
        composable("profile") {
            ProfileScreen(navController, viewModel)
        }
    }
}