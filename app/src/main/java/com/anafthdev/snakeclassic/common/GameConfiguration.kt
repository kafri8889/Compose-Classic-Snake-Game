package com.anafthdev.snakeclassic.common

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import com.anafthdev.snakeclassic.data.Easing
import com.anafthdev.snakeclassic.data.datastore.AppDatastore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class GameConfiguration @Inject constructor(
	private val appDatastore: AppDatastore
) {
	
	var floorSize = 0f
	private set
	
	var movementDelay = 600
	private set
	
	var easingAnimation = Easing.LinearEasing
	private set
	
	var easingAnimationDelay = 250
	private set

	init {
		CoroutineScope(Dispatchers.Main).launch {
			appDatastore.getFloorSize.collect { size ->
				floorSize = size
			}
		}

		CoroutineScope(Dispatchers.Main).launch {
			appDatastore.getMovementDelay.collect { delay ->
				movementDelay = delay
			}
		}

		CoroutineScope(Dispatchers.Main).launch {
			appDatastore.getEasingAnimation.collect { easing ->
				easingAnimation = easing
			}
		}
		
		CoroutineScope(Dispatchers.Main).launch {
			appDatastore.getEasingAnimationDelay.collect { delay ->
				easingAnimationDelay = delay
			}
		}
	}

}

val LocalGameConfiguration: ProvidableCompositionLocal<GameConfiguration?> = compositionLocalOf { null }
