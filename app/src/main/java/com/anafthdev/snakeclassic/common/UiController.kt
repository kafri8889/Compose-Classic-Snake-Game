package com.anafthdev.snakeclassic.common

import androidx.compose.runtime.*

class UiController {
	
	var isNavigationBarShowed by mutableStateOf(true)
	
}

val LocalUiController: ProvidableCompositionLocal<UiController> = compositionLocalOf { UiController() }
