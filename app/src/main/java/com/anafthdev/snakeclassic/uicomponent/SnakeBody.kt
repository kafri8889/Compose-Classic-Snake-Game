package com.anafthdev.snakeclassic.uicomponent

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.anafthdev.snakeclassic.data.Direction
import com.anafthdev.snakeclassic.data.Snake

@Composable
fun SnakeBody(
	snake: Snake,
	floorSize: Dp,
	modifier: Modifier = Modifier
) {

//	Timber.i("bodi: ${snake.bodies.toList()}")
	
	val topEndPercent = remember { Animatable(0f) }
	val topStartPercent = remember { Animatable(0f) }
	val bottomStartPercent = remember { Animatable(0f) }
	val bottomEndPercent = remember { Animatable(0f) }
	
	LaunchedEffect(snake.direction) {
		when (snake.direction) {
			Direction.Up -> {
				topEndPercent.animateTo(
					targetValue = 50f,
					animationSpec = tween(100)
				)
				topStartPercent.animateTo(
					targetValue = 50f,
					animationSpec = tween(100)
				)
				bottomStartPercent.snapTo(
					targetValue = 0f
				)
				bottomEndPercent.snapTo(
					targetValue = 0f
				)
			}
			Direction.Down -> {
				topEndPercent.snapTo(
					targetValue = 0f
				)
				topStartPercent.snapTo(
					targetValue = 0f
				)
				bottomStartPercent.animateTo(
					targetValue = 50f,
					animationSpec = tween(100)
				)
				bottomEndPercent.animateTo(
					targetValue = 50f,
					animationSpec = tween(100)
				)
			}
			Direction.Left -> {
				topEndPercent.snapTo(
					targetValue = 0f
				)
				topStartPercent.animateTo(
					targetValue = 50f,
					animationSpec = tween(100)
				)
				bottomStartPercent.animateTo(
					targetValue = 50f,
					animationSpec = tween(100)
				)
				bottomEndPercent.snapTo(
					targetValue = 0f
				)
			}
			Direction.Right -> {
				topEndPercent.animateTo(
					targetValue = 50f,
					animationSpec = tween(100)
				)
				topStartPercent.snapTo(
					targetValue = 0f
				)
				bottomStartPercent.snapTo(
					targetValue = 0f
				)
				bottomEndPercent.animateTo(
					targetValue = 50f,
					animationSpec = tween(100)
				)
			}
		}
	}
	
	for ((i, body) in snake.bodies.withIndex()) {
		
		val offsetX = remember { Animatable(0f) }
		val offsetY = remember { Animatable(0f) }
		
		LaunchedEffect(body.x) {
			offsetX.animateTo(
				targetValue = body.x.toFloat(),
				animationSpec = tween(
					durationMillis = 450,
					easing = LinearEasing
				)
			)
		}
		
		LaunchedEffect(body.y) {
			offsetY.animateTo(
				targetValue = body.y.toFloat(),
				animationSpec = tween(
					durationMillis = 450,
					easing = LinearEasing
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
				.clip(
					if (i == 0) RoundedCornerShape(
						topEndPercent = topEndPercent.value.toInt(),
						topStartPercent = topStartPercent.value.toInt(),
						bottomStartPercent = bottomStartPercent.value.toInt(),
						bottomEndPercent = bottomEndPercent.value.toInt()
					) else RoundedCornerShape(0)
				)
				.background(
					if (i == 0) Color.Green
					else Color.Red
				)
				.then(modifier)
		)
	}
}
