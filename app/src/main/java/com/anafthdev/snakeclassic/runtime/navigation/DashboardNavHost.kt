package com.anafthdev.snakeclassic.runtime.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.anafthdev.snakeclassic.data.SnakeGameDestination
import com.anafthdev.snakeclassic.ui.dashboard.DashboardContent

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.DashboardNavHost(navController: NavController) {
	navigation(
		startDestination = SnakeGameDestination.Dashboard.Home.route,
		route = SnakeGameDestination.Dashboard.Root.route
	) {
//		composable(
//			route = SnakeGameDestination.Dashboard.Home.route,
//			enterTransition = { scaleIn() },
//			exitTransition = { scaleOut() },
//			popEnterTransition = { scaleIn() },
//			popExitTransition = { scaleOut() }
//		) {
//			DashboardContent(navController = navController)
//		}
		
		composable(SnakeGameDestination.Dashboard.Home.route) {
			DashboardContent(navController = navController)
		}
	}
}
