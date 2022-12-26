package com.anafthdev.snakeclassic.data.datasource.local

import androidx.room.Dao
import androidx.room.Query
import com.anafthdev.snakeclassic.model.Score
import kotlinx.coroutines.flow.Flow

@Dao
interface ScoreReadDAO {
	
	@Query("SELECT * FROM score")
	fun getAll(): Flow<List<Score>>
	
}