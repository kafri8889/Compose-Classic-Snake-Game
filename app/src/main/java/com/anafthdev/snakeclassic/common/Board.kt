package com.anafthdev.snakeclassic.common

import android.util.Size
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.anafthdev.snakeclassic.model.Point

class Board(
	var width: Int = DEFAULT_WIDTH,
	var height: Int = DEFAULT_HEIGHT
) {
	
	private var listener: BoardListener? = null
	
	var foodPosition by mutableStateOf(Point(0,0))
	private set
	
	fun updateFoodPosition(pos: Point) {
		foodPosition = pos
	}
	
	fun updateWidth(newWidth: Int) {
		width = newWidth
		listener?.onSizeChanged(Size(width, height))
	}
	
	fun updateHeight(newHeight: Int) {
		height = newHeight
		listener?.onSizeChanged(Size(width, height))
	}
	
	fun setListener(mListener: BoardListener) {
		listener = mListener
	}
	
	interface BoardListener {
		
		fun onSizeChanged(size: Size)
		
	}

	companion object {
		
		const val DEFAULT_WIDTH = 10
		const val DEFAULT_HEIGHT = 20
	}
}