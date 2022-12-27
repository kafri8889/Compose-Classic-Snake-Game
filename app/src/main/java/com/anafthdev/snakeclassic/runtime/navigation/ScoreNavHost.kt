package com.anafthdev.snakeclassic.runtime.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.anafthdev.snakeclassic.data.SnakeGameDestination
import com.anafthdev.snakeclassic.ui.score.ScoreScreen
import com.anafthdev.snakeclassic.ui.score.ScoreViewModel
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.ScoreAnimatedNavHost(navController: NavController) {
	navigation(
		startDestination = SnakeGameDestination.Score.Home.route,
		route = SnakeGameDestination.Score.Root.route
	) {
		composable(
			route = SnakeGameDestination.Score.Home.route,
			enterTransition = { fadeIn() },
			exitTransition = { fadeOut() },
			popEnterTransition = { fadeIn() },
			popExitTransition = { fadeOut() }
		) { backEntry ->
			val viewModel = hiltViewModel<ScoreViewModel>(backEntry)
			
			ScoreScreen(
				navController = navController,
				viewModel = viewModel
			)
		}
	}
}
