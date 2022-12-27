package com.anafthdev.snakeclassic.ui.dashboard

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.anafthdev.snakeclassic.R
import com.anafthdev.snakeclassic.data.SnakeGameDestination

@Composable
fun DashboardScreen(navController: NavController) {
	
	val context = LocalContext.current
	
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center,
		modifier = Modifier
			.fillMaxSize()
	) {
		Image(
			painter = painterResource(id = R.drawable.ic_app_icon),
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
			
			IconButton(
				onClick = {
					context.startActivity(
						Intent(Intent.ACTION_VIEW).apply {
							flags = Intent.FLAG_ACTIVITY_NEW_TASK
							data = Uri.parse("https://github.com/kafri8889/Compose-Classic-Snake-Game")
						}
					)
				}
			) {
				Icon(
					painter = painterResource(id = R.drawable.ic_github_mark),
					contentDescription = null
				)
			}
		}
	}
}
