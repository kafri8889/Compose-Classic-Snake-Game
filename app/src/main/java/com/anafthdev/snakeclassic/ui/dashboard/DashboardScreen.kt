package com.anafthdev.snakeclassic.ui.dashboard

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.anafthdev.snakeclassic.common.LocalUiController
import com.anafthdev.snakeclassic.data.SnakeGameDestination
import com.anafthdev.snakeclassic.runtime.navigation.DashboardNavHost
import com.anafthdev.snakeclassic.runtime.navigation.GameNavHost

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DashboardScreen() {
	
//	val navController = rememberAnimatedNavController()
//
//	AnimatedNavHost(
//		navController = navController,
//		startDestination = SnakeGameDestination.Dashboard.Root.route
//	) {
//		DashboardNavHost(navController)
//
//		GameNavHost(navController)
//	}
	
	val navController = rememberNavController()

	NavHost(
		navController = navController,
		startDestination = SnakeGameDestination.Dashboard.Root.route
	) {
		DashboardNavHost(navController)

		GameNavHost(navController)
	}
}

@Composable
fun DashboardContent(navController: NavController) {
	
	val uiController = LocalUiController.current
	
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center,
		modifier = Modifier
			.fillMaxSize()
	) {
		Image(
			painter = ColorPainter(Color.LightGray),  // App icon
			contentDescription = null,
			modifier = Modifier
				.size(96.dp)
				.clip(MaterialTheme.shapes.large)
		)
		
		Spacer(modifier = Modifier.padding(24.dp))
		
		Text(
			text = "Highest Score: 0",
			style = MaterialTheme.typography.titleSmall
		)
		
		Spacer(modifier = Modifier.padding(24.dp))
		
		OutlinedButton(
			onClick = {
				uiController.isNavigationBarShowed = false
				
				navController.navigate(SnakeGameDestination.Game.Home.createRoute())
			}
		) {
			Text("Play")
		}
	}
}
