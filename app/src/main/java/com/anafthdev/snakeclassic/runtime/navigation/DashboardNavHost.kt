package com.anafthdev.snakeclassic.runtime.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.anafthdev.snakeclassic.data.SnakeGameDestination
import com.anafthdev.snakeclassic.ui.dashboard.DashboardScreen
import com.google.accompanist.navigation.animation.composable

//fun NavGraphBuilder.DashboardNavHost(navController: NavController) {
//	navigation(
//		startDestination = SnakeGameDestination.Dashboard.Home.route,
//		route = SnakeGameDestination.Dashboard.Root.route
//	) {
//		composable(SnakeGameDestination.Dashboard.Home.route) {
//			DashboardContent(navController = navController)
//		}
//	}
//}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.DashboardAnimatedNavHost(navController: NavController) {
	navigation(
		startDestination = SnakeGameDestination.Dashboard.Home.route,
		route = SnakeGameDestination.Dashboard.Root.route
	) {
		composable(
			route = SnakeGameDestination.Dashboard.Home.route,
			enterTransition = { fadeIn() },
			exitTransition = { fadeOut() },
			popEnterTransition = { fadeIn() },
			popExitTransition = { fadeOut() }
		) {
			DashboardScreen(navController = navController)
		}
	}
}
