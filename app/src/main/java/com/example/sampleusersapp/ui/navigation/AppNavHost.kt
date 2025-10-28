package com.example.sampleusersapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sampleusersapp.ui.views.UserDetailsView
import com.example.sampleusersapp.ui.views.UsersView

@Composable
fun AppNavHost() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppRoutes.USERS) {
        composable(route = AppRoutes.USERS) { UsersView(navController = navController) }
        composable(
            route = "${AppRoutes.USERS_DETAILS}/{${AppRoutesArgs.USER_ID}}",
            arguments = listOf(
                navArgument(AppRoutesArgs.USER_ID) {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
        ) { backStackEntry ->
            UserDetailsView(
                userId = backStackEntry.arguments?.getLong(AppRoutesArgs.USER_ID) ?: -1L,
                onUpClick = { navController.navigateUp() }
            )
        }
    }

}
