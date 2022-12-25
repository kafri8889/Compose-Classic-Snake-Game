package com.anafthdev.snakeclassic.data

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class SnakeGameDestination(val route: String) {
	
	class Dashboard {
		object Root: SnakeGameDestination("dashboard/root")
		object Home: SnakeGameDestination("dashboard/home")
	}
	
	class Setting {
		object Root: SnakeGameDestination("setting/root")
		object Home: SnakeGameDestination("setting/home")
	}
	
	class Score {
		object Root: SnakeGameDestination("score/root")
		object Home: SnakeGameDestination("score/home")
	}
	
	class Game {
		object Root: SnakeGameDestination("game/root")
		object Home: SnakeGameDestination(
			route = "game/home?$ARG_RESTART_GAME={$ARG_RESTART_GAME}"
		) {
			fun createRoute(
				restartGame: Boolean = true
			): String {
				return "game/home?$ARG_RESTART_GAME=$restartGame"
			}
			
			val arguments = listOf(
				navArgument(ARG_RESTART_GAME) {
					type = NavType.BoolType
				}
			)
		}
	}
	
}

const val ARG_RESTART_GAME = "restart_game"
