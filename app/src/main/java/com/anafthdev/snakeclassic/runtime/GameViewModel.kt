package com.anafthdev.snakeclassic.runtime

import androidx.lifecycle.ViewModel
import com.anafthdev.snakeclassic.data.Board
import com.anafthdev.snakeclassic.data.GameEngine
import com.anafthdev.snakeclassic.data.Snake

class GameViewModel : ViewModel() {
	
	val board: Board = Board()
	
	val snake: Snake = Snake()
	
	val gameEngine: GameEngine = GameEngine(
		board = board,
        snake = snake
	)
	
	init {
		gameEngine.start()
	}
	
}