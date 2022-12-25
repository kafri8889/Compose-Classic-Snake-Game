package com.anafthdev.snakeclassic.extension

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import com.anafthdev.snakeclassic.data.Easing

val Easing.easing: androidx.compose.animation.core.Easing
    get() = when (this) {
		Easing.LinearEasing -> LinearEasing
		Easing.FastOutSlowInEasing -> FastOutSlowInEasing
		Easing.LinearOutSlowInEasing -> LinearOutSlowInEasing
		Easing.FastOutLinearInEasing -> FastOutLinearInEasing
	}
