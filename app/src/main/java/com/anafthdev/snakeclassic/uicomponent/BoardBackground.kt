package com.anafthdev.snakeclassic.uicomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun BoardBackground(
	floorSize: Dp,
	boardWidthCount: Int,
	boardHeightCount: Int,
	modifier: Modifier = Modifier
) {
	
	for (y in 0 until boardHeightCount) {
		Row(
			modifier = Modifier
				.offset(y = floorSize * y)
				.then(modifier)
		) {
			for (x in 0 until boardWidthCount) {
				val floorColor = when {
					y % 2 == 0 && x % 2 == 0 -> Color.LightGray
					y % 2 == 0 && x % 2 != 0 -> Color.DarkGray
					y % 2 != 0 && x % 2 == 0 -> Color.DarkGray
					else -> Color.LightGray  // y % 2 != 0 && x % 2 != 0
				}
				
				Box(
					modifier = Modifier
						.size(floorSize)
						.background(floorColor)
				)
			}
		}
	}
}
