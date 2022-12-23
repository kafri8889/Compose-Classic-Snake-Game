package com.anafthdev.snakeclassic.runtime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.anafthdev.snakeclassic.uicomponent.GameBoard
import com.anafthdev.snakeclassic.theme.SnakeClassicTheme

class MainActivity : ComponentActivity() {
	
	private val viewModel: GameViewModel by viewModels()
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		
		setContent {
			SnakeClassicTheme {
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					GameBoard(
						board = viewModel.board,
						snake = viewModel.snake,
						modifier = Modifier
							.fillMaxSize()
							.padding(24.dp)
					)
				}
			}
		}
	}
}
