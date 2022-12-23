package com.anafthdev.snakeclassic.runtime.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.anafthdev.snakeclassic.data.SnakeGameDestination
import com.anafthdev.snakeclassic.ui.game.GameScreen
import com.anafthdev.snakeclassic.ui.game.GameViewModel

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.GameNavHost(navController: NavController) {
	navigation(
		startDestination = SnakeGameDestination.Game.Home.route,
		route = SnakeGameDestination.Game.Root.route
	) {
//		composable(
//			route = SnakeGameDestination.Game.Home.route,
//			enterTransition = { scaleIn() },
//			exitTransition = { scaleOut() },
//			popEnterTransition = { scaleIn() },
//			popExitTransition = { scaleOut() }
//		) {
//			GameScreen(navController = navController)
//		}
		
		composable(
			route = SnakeGameDestination.Game.Home.route,
			arguments = SnakeGameDestination.Game.Home.arguments
		) { backEntry ->
			val viewModel = hiltViewModel<GameViewModel>(backEntry)
			
			GameScreen(
				navController = navController,
				gameViewModel = viewModel
			)
		}
	}
}
