package com.anafthdev.snakeclassic.ui.game

sealed interface GameEffect {
	object GameOver: GameEffect
}