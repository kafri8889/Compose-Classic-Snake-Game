package com.anafthdev.snakeclassic.data.datasource.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.anafthdev.snakeclassic.model.Score

@Dao
abstract class ScoreWriteDAO {
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	abstract suspend fun insertScore(vararg score: Score)
	
	@Delete
	abstract suspend fun deleteScore(vararg score: Score)
	
}
