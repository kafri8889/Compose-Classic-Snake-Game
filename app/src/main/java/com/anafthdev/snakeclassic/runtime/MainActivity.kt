package com.anafthdev.snakeclassic.runtime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.anafthdev.snakeclassic.common.GameConfiguration
import com.anafthdev.snakeclassic.common.LocalGameConfiguration
import com.anafthdev.snakeclassic.theme.SnakeClassicTheme
import com.anafthdev.snakeclassic.ui.main.SnakeNavigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	
	@Inject lateinit var gameConfiguration: GameConfiguration
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		
		WindowCompat.setDecorFitsSystemWindows(window, false)
		
		setContent {
			SnakeClassicTheme {
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					CompositionLocalProvider(
						LocalGameConfiguration provides gameConfiguration
					) {
						SnakeNavigation()
					}
				}
			}
		}
	}
}
