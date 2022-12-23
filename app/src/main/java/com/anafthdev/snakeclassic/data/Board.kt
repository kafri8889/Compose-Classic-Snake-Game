package com.anafthdev.snakeclassic.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.anafthdev.snakeclassic.data.model.Point

class Board(
	var width: Int = DEFAULT_WIDTH,
	var height: Int = DEFAULT_HEIGHT
) {
	
	var foodPosition by mutableStateOf(Point(0,0))
	private set
	
	fun updateFoodPosition(pos: Point) {
		foodPosition = pos
	}

	companion object {
		
		const val DEFAULT_WIDTH = 10
		const val DEFAULT_HEIGHT = 20
	}
}