package com.anafthdev.snakeclassic.runtime.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.anafthdev.snakeclassic.data.SnakeGameDestination
import com.anafthdev.snakeclassic.ui.setting.SettingScreen
import com.anafthdev.snakeclassic.ui.setting.SettingViewModel
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.SettingAnimatedNavHost(navController: NavController) {
	navigation(
		startDestination = SnakeGameDestination.Setting.Home.route,
		route = SnakeGameDestination.Setting.Root.route
	) {
		composable(
			route = SnakeGameDestination.Setting.Home.route,
			enterTransition = { fadeIn() },
			exitTransition = { fadeOut() },
			popEnterTransition = { fadeIn() },
			popExitTransition = { fadeOut() }
		) { backEntry ->
			val viewModel = hiltViewModel<SettingViewModel>(backEntry)
			
			SettingScreen(
				navController = navController,
				viewModel = viewModel
			)
		}
	}
}
