package com.anafthdev.snakeclassic.ui.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anafthdev.snakeclassic.data.ARG_RESTART_GAME
import com.anafthdev.snakeclassic.data.Board
import com.anafthdev.snakeclassic.data.GameEngine
import com.anafthdev.snakeclassic.data.Snake
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle
): ViewModel() {
	
	private val _board: Board = Board()
	
	private val _snake: Snake = Snake()
	
	private val _gameEngine: GameEngine = GameEngine(
		board = _board,
        snake = _snake
	)
	
	private val restartGame: StateFlow<Boolean> = savedStateHandle.getStateFlow(ARG_RESTART_GAME, true)
	
	private val _effect: MutableStateFlow<GameEffect?> = MutableStateFlow(null)
	
	val effect: StateFlow<GameEffect?> = _effect.asStateFlow()
	
	var isPaused by mutableStateOf(true)
	
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