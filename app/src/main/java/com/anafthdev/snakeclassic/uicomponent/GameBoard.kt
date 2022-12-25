package com.anafthdev.snakeclassic.uicomponent

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.anafthdev.snakeclassic.common.Board
import com.anafthdev.snakeclassic.common.LocalGameConfiguration
import com.anafthdev.snakeclassic.common.Snake
import com.anafthdev.snakeclassic.data.Direction.*
import kotlin.math.floor

@Composable
fun GameBoard(
	board: Board,
	snake: Snake,
	modifier: Modifier = Modifier
) {
	
	val gameConfig = LocalGameConfiguration.current!!
	
	val context = LocalContext.current
	val density = LocalDensity.current
	
	var boardSizeDp by remember { mutableStateOf(DpSize(0.dp, 0.dp)) }
	
	val floorSize = remember(gameConfig.floorSize) {
		gameConfig.floorSize.dp
	}
	
	val boardWidth = remember(boardSizeDp.width, floorSize) {
		val width = boardSizeDp.width / floorSize
		
		floor(width).toInt().also { newBoardWidth ->
			board.updateWidth(newBoardWidth)  // Update board width
		}
	}
	
	val boardHeight = remember(boardSizeDp.height, floorSize) {
		val height = boardSizeDp.height / floorSize
		
		floor(height).toInt().also { newBoardHeight ->
			board.updateHeight(newBoardHeight)  // Update board height
		}
	}
	
	Box(
		modifier = modifier
			.onSizeChanged { size ->
				with(density) {
					boardSizeDp = DpSize(size.width.toDp(), size.height.toDp())
				}
			}
	) {
		GestureDetector(
			onUp = {
				if (snake.direction != Down) {
					snake.updateDirection(Up)
				}
				
//				"Up".toast(context)
			},
			onDown = {
				if (snake.direction != Up) {
					snake.updateDirection(Down)
				}
				
//				"Down".toast(context)
			},
			onLeft = {
				if (snake.direction != Right) {
					snake.updateDirection(Left)
				}
				
//				"Left".toast(context)
			},
			onRight = {
				if (snake.direction != Left) {
					snake.updateDirection(Right)
				}
				
//				"Right".toast(context)
			},
			modifier = Modifier
				.matchParentSize()
				.zIndex(4f)
		)
		
		BoardBackground(
			floorSize = floorSize,
			boardWidthCount = boardWidth,
			boardHeightCount = boardHeight,
			modifier = Modifier
				.zIndex(1f)
		)
		
		Food(
			position = board.foodPosition,
			floorSize = floorSize,
			modifier = Modifier
				.zIndex(2f)
		)
		
		SnakeBody(
			snake = snake,
			floorSize = floorSize,
			modifier = Modifier
				.zIndex(3f)
		)
	}
}
