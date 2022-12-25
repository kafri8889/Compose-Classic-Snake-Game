package com.anafthdev.snakeclassic.ui.setting

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.anafthdev.snakeclassic.R
import com.anafthdev.snakeclassic.data.Constant
import com.anafthdev.snakeclassic.data.Easing
import com.anafthdev.snakeclassic.uicomponent.BasicPreference
import com.anafthdev.snakeclassic.uicomponent.SliderVerticalLines

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(
	navController: NavController,
	viewModel: SettingViewModel
) {

	Column(
		modifier = Modifier
			.fillMaxSize()
			.verticalScroll(rememberScrollState())
	) {
		CenterAlignedTopAppBar(
			title = {
				Text("Setting")
			},
			navigationIcon = {
				IconButton(
					onClick = {
						navController.popBackStack()
					}
				) {
					Icon(
						imageVector = Icons.Rounded.ArrowBackIosNew,
						contentDescription = null
					)
				}
			}
		)
		
		FloorSizePreference(
			floorSize = viewModel.floorSize
		) { size ->
			viewModel.updateFloorSize(size)
		}
		
		MovementDelayPreference(
			movementDelay = viewModel.movementDelay,
			onMovementDelayChanged = { delay ->
				viewModel.updateMovementDelay(delay)
			}
		)
		
		EasingAnimationPreference(
			easing = viewModel.easingAnimation,
			onEasingAnimationChanged = { easing ->
				viewModel.updateEasingAnimation(easing)
			}
		)
		
		EasingAnimationDelayPreference(
			delay = viewModel.easingAnimationDelay,
			onEasingAnimationDelayChanged = { delay ->
				viewModel.updateEasingAnimationDelay(delay)
			}
		)
	}
	
}

@Composable
private fun FloorSizePreference(
	floorSize: Float,
	onFloorSizeChanged: (Float) -> Unit
) {
	
	var floorSizeSliderVisible by remember { mutableStateOf(false) }
	
	BasicPreference(
		showValue = true,
		title = {
			Text("Floor Size")
		},
		icon = {
			Icon(
				painter = painterResource(id = R.drawable.ic_maximize),
				contentDescription = null
			)
		},
		value = {
			Text("${floorSize.toInt()} dp")
		},
		onClick = {
			floorSizeSliderVisible = !floorSizeSliderVisible
		},
		modifier = Modifier
			.fillMaxWidth()
	)
	
	AnimatedVisibility(
		visible = floorSizeSliderVisible,
		enter = expandVertically(
			initialHeight = { -it },
			animationSpec = tween(400)
		) + fadeIn(
			animationSpec = tween(200)
		),
		exit = shrinkVertically(
			targetHeight = { -it },
            animationSpec = tween(400)
		) + fadeOut(
			animationSpec = tween(200)
		)
	) {
		Column(
			modifier = Modifier
				.padding(
					horizontal = 8.dp
				)
		) {
			Box(
				contentAlignment = Alignment.Center
			) {
				SliderVerticalLines(
					rem = 6,
					labels = Constant.floorSizeLabelsPreference,
					lineColor = Color.DarkGray,
					lineHeight = 8.dp,
					modifier = Modifier.matchParentSize()
				)
				
				Slider(
					steps = 29,
					valueRange = 2f..32f,
					value = floorSize,
					onValueChange = onFloorSizeChanged,
					colors = SliderDefaults.colors(
						activeTickColor = Color.Transparent,
						inactiveTickColor = Color.Transparent
					),
					modifier = Modifier
						.fillMaxWidth()
				)
			}
			
			Spacer(modifier = Modifier.height(4.dp))
			
			Divider(modifier = Modifier.fillMaxWidth())
		}
	}
}

@Composable
private fun MovementDelayPreference(
	movementDelay: Int,
	onMovementDelayChanged: (Int) -> Unit
) {
	
	var movementDelaySliderVisible by remember { mutableStateOf(false) }
	
	BasicPreference(
		showValue = true,
		title = {
			Text("Movement Delay")
		},
		icon = {
			Icon(
				painter = painterResource(id = R.drawable.ic_timer),
				contentDescription = null
			)
		},
		value = {
			Text("$movementDelay ms")
		},
		onClick = {
			movementDelaySliderVisible = !movementDelaySliderVisible
		},
		modifier = Modifier
			.fillMaxWidth()
	)
	
	AnimatedVisibility(
		visible = movementDelaySliderVisible,
		enter = expandVertically(
			initialHeight = { -it },
			animationSpec = tween(400)
		) + fadeIn(
			animationSpec = tween(200)
		),
		exit = shrinkVertically(
			targetHeight = { -it },
            animationSpec = tween(400)
		) + fadeOut(
			animationSpec = tween(200)
		)
	) {
		Column(
			modifier = Modifier
				.padding(
					horizontal = 8.dp
				)
		) {
			Box(
				contentAlignment = Alignment.Center
			) {
				SliderVerticalLines(
					labels = Constant.movementDelayLabelsPreference,
					lineColor = Color.DarkGray,
					lineHeight = 8.dp,
					modifier = Modifier.matchParentSize()
				)
				
				Slider(
					steps = 999,
					valueRange = 0f..1000f,
					value = movementDelay.toFloat(),
					onValueChange = { delay ->
						onMovementDelayChanged(delay.toInt())
					},
					colors = SliderDefaults.colors(
						activeTickColor = Color.Transparent,
						inactiveTickColor = Color.Transparent
					),
					modifier = Modifier
						.fillMaxWidth()
				)
			}
			
			Spacer(modifier = Modifier.height(4.dp))
			
			Divider(modifier = Modifier.fillMaxWidth())
		}
	}
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun EasingAnimationPreference(
	easing: Easing,
	onEasingAnimationChanged: (Easing) -> Unit
) {
	
	var easingAnimationSelectorVisible by remember { mutableStateOf(false) }
	
	BasicPreference(
		showValue = true,
		title = {
			Text(
				text = "Snake Easing Animation",
				overflow = TextOverflow.Ellipsis
			)
		},
		icon = {
			Icon(
				painter = painterResource(id = R.drawable.ic_animation),
				contentDescription = null
			)
		},
		value = {
			Text("$easing")
		},
		onClick = {
			easingAnimationSelectorVisible = !easingAnimationSelectorVisible
		},
		modifier = Modifier
			.fillMaxWidth()
	)
	
	AnimatedVisibility(
		visible = easingAnimationSelectorVisible,
		enter = expandVertically(
			initialHeight = { -it },
			animationSpec = tween(400)
		) + fadeIn(
			animationSpec = tween(200)
		),
		exit = shrinkVertically(
			targetHeight = { -it },
            animationSpec = tween(400)
		) + fadeOut(
			animationSpec = tween(200)
		)
	) {
		Column(
			modifier = Modifier
				.padding(
					horizontal = 8.dp
				)
		) {
			LazyRow {
				items(Easing.values()) { easingAnim ->
					FilterChip(
						selected = easingAnim == easing,
						label = {
							Text("$easingAnim")
						},
						leadingIcon = {
							AnimatedVisibility(
								visible = easingAnim == easing,
								enter = scaleIn(tween(250)),
								exit = scaleOut(tween(250))
							) {
								Icon(
									imageVector = Icons.Rounded.Check,
									contentDescription = null
								)
							}
						},
						onClick = {
							onEasingAnimationChanged(easingAnim)
						},
						modifier = Modifier
							.padding(horizontal = 4.dp)
					)
				}
			}
			
			Spacer(modifier = Modifier.height(4.dp))
			
			Divider(modifier = Modifier.fillMaxWidth())
		}
	}
}

@Composable
private fun EasingAnimationDelayPreference(
	delay: Int,
	onEasingAnimationDelayChanged: (Int) -> Unit
) {
	
	var easingAnimationDelaySliderVisible by remember { mutableStateOf(false) }
	
	BasicPreference(
		showValue = true,
		title = {
			Text(
				text = "Snake Easing Animation Delay",
				overflow = TextOverflow.Ellipsis
			)
		},
		icon = {
			Icon(
				painter = painterResource(id = R.drawable.ic_animation),
				contentDescription = null
			)
		},
		value = {
			Text("$delay ms")
		},
		onClick = {
			easingAnimationDelaySliderVisible = !easingAnimationDelaySliderVisible
		},
		modifier = Modifier
			.fillMaxWidth()
	)
	
	AnimatedVisibility(
		visible = easingAnimationDelaySliderVisible,
		enter = expandVertically(
			initialHeight = { -it },
			animationSpec = tween(400)
		) + fadeIn(
			animationSpec = tween(200)
		),
		exit = shrinkVertically(
			targetHeight = { -it },
			animationSpec = tween(400)
		) + fadeOut(
			animationSpec = tween(200)
		)
	) {
		Column(
			modifier = Modifier
				.padding(
					horizontal = 8.dp
				)
		) {
			Box(
				contentAlignment = Alignment.Center
			) {
				SliderVerticalLines(
					labels = Constant.movementDelayLabelsPreference,
					lineColor = Color.DarkGray,
					lineHeight = 8.dp,
					modifier = Modifier.matchParentSize()
				)
				
				Slider(
					steps = 999,
					valueRange = 0f..1000f,
					value = delay.toFloat(),
					onValueChange = { delay ->
						onEasingAnimationDelayChanged(delay.toInt())
					},
					colors = SliderDefaults.colors(
						activeTickColor = Color.Transparent,
						inactiveTickColor = Color.Transparent
					),
					modifier = Modifier
						.fillMaxWidth()
				)
			}
			
			Spacer(modifier = Modifier.height(4.dp))
			
			Divider(modifier = Modifier.fillMaxWidth())
		}
	}
}
