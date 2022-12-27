package com.anafthdev.snakeclassic.ui.dashboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anafthdev.snakeclassic.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
	private val repository: Repository
): ViewModel() {
	
	var highestScore by mutableStateOf(0)
	private set
	
	init {
		viewModelScope.launch {
			repository.getHighestScore().collect { score ->
				highestScore = score.score
			}
		}
	}
	
}