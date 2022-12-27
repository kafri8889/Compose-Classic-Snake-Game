package com.anafthdev.snakeclassic.ui.game

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.anafthdev.snakeclassic.uicomponent.GameBoard
import com.anafthdev.snakeclassic.uicomponent.GameOverDialog
import com.anafthdev.snakeclassic.uicomponent.GamePauseDialog

@Composable
fun GameScreen(
	navController: NavController,
	gameViewModel: GameViewModel
) {
	
	val effect by gameViewModel.effect.collectAsState()
	
	// prevent popup twice
	// because the popup still appears for a while when the user click exit button
	var hasPopped by remember { mutableStateOf(false) }
	
//	LaunchedEffect(effect) {
//		when (effect) {
//			is GameEffect.GameOver -> {
//
//			}
//			else -> {}
//		}
//	}
	
	BackHandler {
		when {
			gameViewModel.isPaused -> {
				gameViewModel.resume()
			}
			!gameViewModel.isPaused -> {
				gameViewModel.pause()
			}
		}
	}
	
	Box(
		modifier = Modifier
			.animateContentSize(
				animationSpec = tween(250)
			)
	) {
		GameBoard(
			board = gameViewModel.board,
			snake = gameViewModel.snake,
			modifier = Modifier
				.fillMaxSize()
				.padding(24.dp)
		)
		
		AnimatedVisibility(visible = gameViewModel.isPaused) {
			GamePauseDialog(
				currentScore = gameViewModel.score,
				onExit = {
					if (!hasPopped) {
						navController.popBackStack()
					}
					
					hasPopped = true
				},
				onRestart = {
					gameViewModel.restart()
				},
				onResume = {
					gameViewModel.resume()
				}
			)
		}
		
		AnimatedVisibility(visible = gameViewModel.isGameOver) {
			GameOverDialog(
				score = gameViewModel.score,
				onExit = {
					if (!hasPopped) {
						navController.popBackStack()
					}
					
					hasPopped = true
				},
				onRestart = {
					gameViewModel.start()
				}
			)
		}
	}

}
