package com.anafthdev.snakeclassic.data.datasource.local

import androidx.room.Dao
import androidx.room.Query
import com.anafthdev.snakeclassic.model.Score
import kotlinx.coroutines.flow.Flow

@Dao
interface ScoreReadDAO {
	
	@Query("SELECT * FROM score")
	fun getAll(): Flow<List<Score>>
	
	@Query("SELECT * FROM score ORDER BY score DESC LIMIT 1")
	fun getHighestScore(): Flow<Score>
	
}