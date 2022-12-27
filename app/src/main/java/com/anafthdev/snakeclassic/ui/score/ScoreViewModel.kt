package com.anafthdev.snakeclassic.ui.score

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anafthdev.snakeclassic.data.repository.Repository
import com.anafthdev.snakeclassic.model.Score
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScoreViewModel @Inject constructor(
	private val repository: Repository
): ViewModel() {
	
	val scores = mutableStateListOf<Score>()
	
	init {
		viewModelScope.launch {
			repository.getAllScore().collect { scoreList ->
				scores.apply {
					clear()
					addAll(scoreList)
				}
			}
		}
	}
	
	fun deleteScore(score: Score) {
		viewModelScope.launch {
			repository.deleteScore(score)
		}
	}

}