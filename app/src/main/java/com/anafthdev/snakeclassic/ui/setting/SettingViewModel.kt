package com.anafthdev.snakeclassic.ui.setting

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anafthdev.snakeclassic.data.Easing
import com.anafthdev.snakeclassic.data.datastore.AppDatastore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
	private val appDatastore: AppDatastore
): ViewModel() {
	
	var floorSize by mutableStateOf(0f)
	private set
	
	var movementDelay by mutableStateOf(600)
	private set
	
	var easingAnimation by mutableStateOf(Easing.LinearEasing)
	private set
	
	var easingAnimationDelay by mutableStateOf(250)
	private set
	
	init {
		viewModelScope.launch {
			appDatastore.getFloorSize.collect { size ->
				floorSize = size
			}
		}
		
		viewModelScope.launch {
			appDatastore.getMovementDelay.collect { delay ->
				movementDelay = delay
			}
		}
		
		viewModelScope.launch {
			appDatastore.getEasingAnimation.collect { easing ->
				easingAnimation = easing
			}
		}
		
		viewModelScope.launch {
			appDatastore.getEasingAnimationDelay.collect { delay ->
				easingAnimationDelay = delay
			}
		}
	}
	
	fun updateFloorSize(size: Float) {
		viewModelScope.launch {
			appDatastore.setFloorSize(size)
		}
	}
	
	fun updateMovementDelay(delay: Int) {
		viewModelScope.launch {
			appDatastore.setMovementDelay(delay)
		}
	}
	
	fun updateEasingAnimation(easing: Easing) {
		viewModelScope.launch {
			appDatastore.setEasingAnimation(easing)
		}
	}
	
	fun updateEasingAnimationDelay(delay: Int) {
		viewModelScope.launch {
			appDatastore.setEasingAnimationDelay(delay)
		}
	}

}