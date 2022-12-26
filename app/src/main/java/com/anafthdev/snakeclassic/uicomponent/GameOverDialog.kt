package com.anafthdev.snakeclassic.uicomponent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GameOverDialog(
	score: Int,
	onRestart: () -> Unit,
	onExit: () -> Unit
) {
	
	SnakeAlertDialogContent(
		text = {
			Column(
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				Text("Game Over")
				Text("Score: $score")
			}
		},
		buttons = {
			Row {
				OutlinedButton(onClick = onRestart) {
					Text("Restart")
				}
				
				Spacer(modifier = Modifier.width(8.dp))
				
				OutlinedButton(onClick = onExit) {
					Text("Exit")
				}
			}
		}
	)
}
