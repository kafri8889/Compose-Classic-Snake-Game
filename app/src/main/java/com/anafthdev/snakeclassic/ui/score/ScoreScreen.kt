package com.anafthdev.snakeclassic.ui.score

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoreScreen(
	navController: NavController,
	
) {
	
	LazyColumn(
		modifier = Modifier
			.fillMaxSize()
	) {
		item {
			CenterAlignedTopAppBar(
				title = {
					Text("Score")
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
		}
		
		
	}
}
