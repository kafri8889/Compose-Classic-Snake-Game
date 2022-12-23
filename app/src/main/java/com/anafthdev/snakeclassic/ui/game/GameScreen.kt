package com.anafthdev.snakeclassic.ui.game

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.anafthdev.snakeclassic.common.LocalUiController
import com.anafthdev.snakeclassic.uicomponent.GameBoard
import com.anafthdev.snakeclassic.uicomponent.GamePauseDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun GameScreen(
	navController: NavController,
	gameViewModel: GameViewModel
) {
	
	val uiController = LocalUiController.current
	
	val scope = rememberCoroutineScope()
	
	val effect by gameViewModel.effect.collectAsState()
	
	// prevent popup twice
	// because the popup still appears for a while when the user click exit button
	var hasPopped by remember { mutableStateOf(false) }
	
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
		)
		
		AnimatedVisibility(visible = gameViewModel.isPaused) {
			GamePauseDialog(
				currentScore = 0,
				onExit = {
					if (!hasPopped) {
						scope.launch {
							delay(300)
							
							withContext(Dispatchers.Main) {
								uiController.isNavigationBarShowed = true
							}
						}
						
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
	}

}
