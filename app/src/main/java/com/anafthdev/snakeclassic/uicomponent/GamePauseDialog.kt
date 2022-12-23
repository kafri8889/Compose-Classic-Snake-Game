package com.anafthdev.snakeclassic.uicomponent

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun GamePauseDialog(
	currentScore: Int,
	onExit: () -> Unit,
	onRestart: () -> Unit,
	onResume: () -> Unit,
) {

	SnakeAlertDialogContent(
		text = {
			Text(
				text = "Score: $currentScore"
			)
		},
		buttons = {
			FlowRow(
				lastLineMainAxisAlignment = FlowMainAxisAlignment.Center
			) {
				OutlinedButton(onClick = onResume) {
					Text("Resume")
				}
				
				Spacer(modifier = Modifier.width(8.dp))
				
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
