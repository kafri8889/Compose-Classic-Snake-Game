package com.anafthdev.snakeclassic.ui.score

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.anafthdev.snakeclassic.R
import com.anafthdev.snakeclassic.model.Score
import com.anafthdev.snakeclassic.uicomponent.ScoreItem

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ScoreScreen(
	navController: NavController,
	viewModel: ScoreViewModel
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
		
		itemsIndexed(
			items = viewModel.scores,
			key = { _, item: Score -> item.id }
		) { i, score ->
			val dismissState = rememberDismissState(
				confirmStateChange = { dismissValue ->
					if (dismissValue == DismissValue.DismissedToEnd) {
						viewModel.deleteScore(score)
					}
					
					return@rememberDismissState false
				}
			)
			
			SwipeToDismiss(
				state = dismissState,
				directions = setOf(
					DismissDirection.StartToEnd
				),
				background = {
					Box(
						modifier = Modifier
							.fillMaxSize()
							.clip(MaterialTheme.shapes.large)
							.background(Color(0xFFFFA1A1))
							.align(Alignment.CenterVertically)
					) {
						Icon(
							painter = painterResource(id = R.drawable.ic_trash),
							contentDescription = null,
							modifier = Modifier
								.padding(horizontal = 16.dp)
								.align(Alignment.CenterStart)
						)
					}
					
					Icon(
						painter = painterResource(id = R.drawable.ic_trash),
						contentDescription = null,
						modifier = Modifier
							.padding(horizontal = 16.dp)
							.align(Alignment.CenterVertically)
					)
				},
				modifier = Modifier
					.padding(8.dp)
			) {
				ScoreItem(
					index = i,
					score = score
				)
			}
		}
	}
}
