package com.anafthdev.snakeclassic.uicomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.anafthdev.snakeclassic.model.Point

@Composable
fun Food(
	position: Point,
	floorSize: Dp,
	modifier: Modifier = Modifier
) {
	
	Box(
		modifier = Modifier
			.size(floorSize)
			.offset(
				x = floorSize * position.x,
				y = floorSize * position.y
			)
			.background(Color.Magenta)
			.then(modifier)
	)
}
