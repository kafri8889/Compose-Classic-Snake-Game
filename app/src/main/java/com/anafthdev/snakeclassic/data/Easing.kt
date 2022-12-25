package com.anafthdev.snakeclassic.data

enum class Easing {
	LinearEasing,
	FastOutSlowInEasing,
	LinearOutSlowInEasing,
	FastOutLinearInEasing;
	
	override fun toString(): String {
		return when (this) {
			LinearEasing -> "Linear"
			FastOutSlowInEasing -> "Fast Out Slow In"
			LinearOutSlowInEasing -> "Linear Out Slow In"
			FastOutLinearInEasing -> "Fast Out Linear In"
		}
	}
}