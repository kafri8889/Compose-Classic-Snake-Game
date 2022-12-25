package com.anafthdev.snakeclassic.ui.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.anafthdev.snakeclassic.R
import com.anafthdev.snakeclassic.data.SnakeGameDestination

@Composable
fun DashboardScreen(navController: NavController) {
	
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
		
		Spacer(modifier = Modifier.padding(8.dp))
		
		Text(
			text = "Highest Score: 0",
			style = MaterialTheme.typography.titleSmall
		)
		
		Spacer(modifier = Modifier.padding(8.dp))
		
		OutlinedButton(
			onClick = {
				navController.navigate(SnakeGameDestination.Game.Home.createRoute())
			}
		) {
			Text("Play")
		}
		
		Spacer(modifier = Modifier.padding(16.dp))
		
		Row(
			verticalAlignment = Alignment.CenterVertically
		) {
			IconButton(
				onClick = {
					navController.navigate(SnakeGameDestination.Setting.Home.route)
				}
			) {
				Icon(
					painter = painterResource(id = R.drawable.ic_setting),
					contentDescription = null
				)
			}
			
			IconButton(
				onClick = {
					navController.navigate(SnakeGameDestination.Score.Home.route)
				}
			) {
				Icon(
					painter = painterResource(id = R.drawable.ic_award),
					contentDescription = null
				)
			}
		}
	}
}
