package com.anafthdev.snakeclassic.uicomponent

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.anafthdev.snakeclassic.common.LocalGameConfiguration
import com.anafthdev.snakeclassic.common.Snake
import com.anafthdev.snakeclassic.extension.easing

@Composable
fun SnakeBody(
	snake: Snake,
	floorSize: Dp,
	modifier: Modifier = Modifier
) {
	
	val gameConfig = LocalGameConfiguration.current!!

//	Timber.i("bodi: ${snake.bodies.toList()}")
	
	for ((i, body) in snake.bodies.withIndex()) {
		
		val offsetX = remember { Animatable(0f) }
		val offsetY = remember { Animatable(0f) }
		
		LaunchedEffect(body.x) {
			offsetX.animateTo(
				targetValue = body.x.toFloat(),
				animationSpec = tween(
					durationMillis = gameConfig.easingAnimationDelay,
					easing = gameConfig.easingAnimation.easing
				)
			)
		}
		
		LaunchedEffect(body.y) {
			offsetY.animateTo(
				targetValue = body.y.toFloat(),
				animationSpec = tween(
					durationMillis = gameConfig.easingAnimationDelay,
					easing = gameConfig.easingAnimation.easing
				)
			)
		}
		
		Box(
			modifier = Modifier
				.size(floorSize)
				.offset(
					x = floorSize * offsetX.value,
					y = floorSize * offsetY.value
				)
				.background(
					if (i == 0) Color.Green
					else Color.Red
				)
				.then(modifier)
		)
	}
}
