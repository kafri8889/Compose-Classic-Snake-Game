package com.anafthdev.snakeclassic.data.repository

import com.anafthdev.snakeclassic.data.datasource.local.LocalDatasource
import com.anafthdev.snakeclassic.model.Score
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
	private val localDatasource: LocalDatasource
) {
	
	fun getAllScore(): Flow<List<Score>> {
		return localDatasource.getAllScore()
	}
	
	fun getHighestScore(): Flow<Score> {
		return localDatasource.getHighestScore()
	}
	
	
	
	suspend fun insertScore(vararg score: Score) {
		localDatasource.insertScore(*score)
	}
	
	suspend fun deleteScore(vararg score: Score) {
		localDatasource.deleteScore(*score)
	}
	
}