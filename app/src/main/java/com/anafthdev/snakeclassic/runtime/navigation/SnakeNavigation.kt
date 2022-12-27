package com.anafthdev.snakeclassic.runtime.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.anafthdev.snakeclassic.data.SnakeGameDestination
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(
	ExperimentalAnimationApi::class
)
@Composable
fun SnakeNavigation() {
	
	val scope = rememberCoroutineScope()
	val systemUiController = rememberSystemUiController()
	val navController = rememberAnimatedNavController()
	
	SideEffect {
		systemUiController.setSystemBarsColor(
			color = Color.Transparent,
			darkIcons = true
		)
	}
	
	AnimatedNavHost(
		navController = navController,
		startDestination = SnakeGameDestination.Dashboard.Root.route,
		modifier = Modifier
			.fillMaxSize()
	) {
		DashboardAnimatedNavHost(navController)
		
		SettingAnimatedNavHost(navController)
		
		ScoreAnimatedNavHost(navController)
		
		GameAnimatedNavHost(navController)
	}
}
