package com.anafthdev.snakeclassic.uicomponent

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.anafthdev.snakeclassic.model.Score

@Composable
fun ScoreItem(
	index: Int,
	score: Score,
	modifier: Modifier = Modifier
) {
	
	Card(
		shape = MaterialTheme.shapes.large,
		modifier = modifier
	) {
		Padding(all = 8.dp) {
			Row(
				horizontalArrangement = Arrangement.SpaceEvenly,
				modifier = Modifier
					.fillMaxWidth()
			) {
				Column(horizontalAlignment = Alignment.CenterHorizontally) {
					Text(
						text = "${score.score}",
						style = MaterialTheme.typography.titleMedium.copy(
							fontWeight = FontWeight.Bold
						)
					)
					
					Spacer(modifier = Modifier.height(8.dp))
					
					Text(
						text = "Score",
						style = MaterialTheme.typography.bodySmall.copy(
							fontWeight = FontWeight.Light
						)
					)
				}
				
				Column(horizontalAlignment = Alignment.CenterHorizontally) {
					Text(
						text = "${score.gameConfigurationData.floorSize} dp",
						style = MaterialTheme.typography.titleMedium.copy(
							fontWeight = FontWeight.Bold
						)
					)
					
					Spacer(modifier = Modifier.height(8.dp))
					
					Text(
						text = "Floor size",
						style = MaterialTheme.typography.bodySmall.copy(
							fontWeight = FontWeight.Light
						)
					)
				}
			}
			
			Spacer(modifier = Modifier.height(8.dp))
			
			Row(
				horizontalArrangement = Arrangement.SpaceBetween,
				modifier = Modifier
					.fillMaxWidth()
			) {
				Column(horizontalAlignment = Alignment.CenterHorizontally) {
					Text(
						text = "${score.gameConfigurationData.movementDelay} ms",
						style = MaterialTheme.typography.titleMedium.copy(
							fontWeight = FontWeight.Bold
						)
					)
					
					Spacer(modifier = Modifier.height(8.dp))
					
					Text(
						text = "Movement delay",
						style = MaterialTheme.typography.bodySmall.copy(
							fontWeight = FontWeight.Light
						)
					)
				}
				
				Column(horizontalAlignment = Alignment.CenterHorizontally) {
					Text(
						text = "${score.gameConfigurationData.easingAnimationDelay} ms",
						style = MaterialTheme.typography.titleMedium.copy(
							fontWeight = FontWeight.Bold
						)
					)
					
					Spacer(modifier = Modifier.height(8.dp))
					
					Text(
						text = "Easing animation delay",
						style = MaterialTheme.typography.bodySmall.copy(
							fontWeight = FontWeight.Light
						)
					)
				}
			}
		}
	}
}
