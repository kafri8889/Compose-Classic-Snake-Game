package com.anafthdev.snakeclassic.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "score")
data class Score(
	@PrimaryKey
	@ColumnInfo(name = "id") val id: Int,
	
	@ColumnInfo(name = "score") val score: Int,
	
	@ColumnInfo(name = "gameConfigData") val gameConfigurationData: GameConfigurationData,
)
