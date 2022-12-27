package com.anafthdev.snakeclassic.ui.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anafthdev.snakeclassic.common.Board
import com.anafthdev.snakeclassic.common.GameConfiguration
import com.anafthdev.snakeclassic.common.GameEngine
import com.anafthdev.snakeclassic.common.Snake
import com.anafthdev.snakeclassic.data.ARG_RESTART_GAME
import com.anafthdev.snakeclassic.data.repository.Repository
import com.anafthdev.snakeclassic.model.GameConfigurationData
import com.anafthdev.snakeclassic.model.Score
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class GameViewModel @Inject constructor(
	private val repository: Repository,
	savedStateHandle: SavedStateHandle,
	gameConfiguration: GameConfiguration
): ViewModel() {
	
	private val _board: Board = Board()
	
	private val _snake: Snake = Snake()
	
	private val _gameEngine: GameEngine = GameEngine(
		board = _board,
        snake = _snake,
		gameConfiguration = gameConfiguration
	)
	
	private val restartGame: StateFlow<Boolean> = savedStateHandle.getStateFlow(ARG_RESTART_GAME, true)
	
	private val _effect: MutableStateFlow<GameEffect?> = MutableStateFlow(null)
	
	val effect: StateFlow<GameEffect?> = _effect.asStateFlow()
	
	var score by mutableStateOf(0)
	var isPaused by mutableStateOf(true)
	var isGameOver by mutableStateOf(false)
	
	val board: Board = _board
	
	val snake: Snake = _snake
	
	init {
		viewModelScope.launch {
			restartGame.collect { restart ->
				if (restart) restart()
			}
		}
		
		_gameEngine.setListener(object : GameEngine.GameListener {
			override fun isPlaying(playing: Boolean) {
				isPaused = !playing
			}
			
			override fun onGameOver() {
				_effect.value = GameEffect.GameOver
				isGameOver = true
				
				viewModelScope.launch {
					repository.insertScore(
						Score(
							id = Random.nextInt(),
							score = score,
							gameConfigurationData = GameConfigurationData(
								floorSize = gameConfiguration.floorSize,
								movementDelay = gameConfiguration.movementDelay,
								easingAnimationDelay = gameConfiguration.easingAnimationDelay
							)
						)
					)
				}
			}
			
			override fun onRestart() {
				isGameOver = false
				isPaused = true
				score = 0
			}
			
			override fun onEat() {
				score++
			}
		})
		
		start()
	}
	
	fun start() {
		_gameEngine.start()
	}
	
	fun pause() {
        _gameEngine.pause()
    }
	
	fun resume() {
        _gameEngine.resume()
    }
	
	fun restart() {
        _gameEngine.restart()
    }
	
	fun stop() {
        _gameEngine.stop()
    }
	
}