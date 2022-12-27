package com.anafthdev.snakeclassic.data.datasource.local

import com.anafthdev.snakeclassic.model.Score
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDatasource @Inject constructor(
	private val scoreReadDAO: ScoreReadDAO,
	private val scoreWriteDAO: ScoreWriteDAO
) {

	fun getAllScore(): Flow<List<Score>> {
		return scoreReadDAO.getAll()
	}
	
	fun getHighestScore(): Flow<Score> {
		return scoreReadDAO.getHighestScore()
	}
	
	
	
	suspend fun insertScore(vararg score: Score) {
		scoreWriteDAO.insertScore(*score)
	}
	
	suspend fun deleteScore(vararg score: Score) {
		scoreWriteDAO.deleteScore(*score)
	}
	
}