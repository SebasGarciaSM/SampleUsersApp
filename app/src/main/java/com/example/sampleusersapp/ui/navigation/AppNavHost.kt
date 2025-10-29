package com.example.sampleusersapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sampleusersapp.data.local.NavigationAction
import com.example.sampleusersapp.domain.interfaces.INavigationReceiver
import com.example.sampleusersapp.ui.views.UserDetailsView
import com.example.sampleusersapp.ui.views.UsersView
import kotlinx.coroutines.launch

@Composable
fun AppNavHost(navigationReceiver: INavigationReceiver) {

    val navController = rememberNavController()

    LifecycleResumeEffect(Unit) {
        val navigationBusJob = lifecycleScope.launch {
            navigationReceiver.navigation.collect { action ->
                when (action) {
                    is NavigationAction.NavigateBack -> navController.navigateUp()
                    is NavigationAction.NavigateTo -> {
                        if (action.clearBackStack) {
                            navController.navigate(action.route) {
                                popUpTo(navController.graph.startDestinationId) { inclusive = true }
                            }
                        } else {
                            navController.navigate(action.route)
                        }
                    }

                    is NavigationAction.NavigateToWithArgs -> {
                        val routeWithArgs = action.route + action.args.entries.joinToString(
                            prefix = "/",
                            separator = "/",
                            transform = { it.value }
                        )

                        if (action.clearBackStack) {
                            navController.navigate(action.route) {
                                popUpTo(navController.graph.startDestinationId) { inclusive = true }
                            }
                        } else {
                            navController.navigate(routeWithArgs)
                        }
                    }
                }
            }
        }

        onPauseOrDispose {
            navigationBusJob.cancel()
        }
    }


    NavHost(navController = navController, startDestination = AppRoutes.USERS) {
        composable(route = AppRoutes.USERS) { UsersView() }
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
