package com.anafthdev.snakeclassic.ui.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.anafthdev.snakeclassic.common.LocalUiController
import com.anafthdev.snakeclassic.data.Constant
import com.anafthdev.snakeclassic.ui.dashboard.DashboardScreen
import com.anafthdev.snakeclassic.ui.score.ScoreScreen
import com.anafthdev.snakeclassic.ui.setting.SettingScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
	
	val uiController = LocalUiController.current
	
	val scope = rememberCoroutineScope()
	val pagerState = rememberPagerState()
	val systemUiController = rememberSystemUiController()
	
	SideEffect {
		systemUiController.setSystemBarsColor(
			color = Color.Transparent,
			darkIcons = true
		)
	}
	
	Scaffold(
		bottomBar = {
			AnimatedVisibility(
				visible = uiController.isNavigationBarShowed,
				enter = slideInVertically(
					initialOffsetY = { it },
					animationSpec = tween(600)
				),
				exit = slideOutVertically(
					targetOffsetY = { it },
					animationSpec = tween(600)
				)
			) {
				NavigationBar {
					Constant.navBarItems.forEachIndexed { i, (icon, label) ->
						NavigationBarItem(
							selected = pagerState.currentPage == i,
							alwaysShowLabel = false,
							icon = {
								Icon(
									painter = painterResource(id = icon),
									contentDescription = null
								)
							},
							label = {
								Text(label)
							},
							onClick = {
								scope.launch {
									pagerState.animateScrollToPage(i)
								}
							}
						)
					}
				}
			}
		}
	) { scaffoldPadding ->
		HorizontalPager(
			state = pagerState,
			pageCount = 3,
			modifier = Modifier
				.padding(scaffoldPadding)
		) { page ->
			when (page) {
				0 -> DashboardScreen()
				1 -> ScoreScreen()
				2 -> SettingScreen()
			}
		}
	}
}
