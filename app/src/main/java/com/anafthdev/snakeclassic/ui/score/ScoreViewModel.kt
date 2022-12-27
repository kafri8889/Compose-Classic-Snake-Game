package com.anafthdev.snakeclassic.ui.score

import androidx.lifecycle.ViewModel
import com.anafthdev.snakeclassic.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScoreViewModel @Inject constructor(
	private val repository: Repository
): ViewModel() {



}