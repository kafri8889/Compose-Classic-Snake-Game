package com.anafthdev.snakeclassic.data.datasource.local

import androidx.room.TypeConverter
import com.anafthdev.snakeclassic.common.GameConfiguration
import com.google.gson.Gson

object AppDatabaseTypeConverter {
	
	@TypeConverter
	fun gameConfigurationDataToJson(gameConfiguration: GameConfiguration) = Gson().toJson(gameConfiguration)
	
	@TypeConverter
	fun gameConfigurationDataFromJson(json: String) = Gson().fromJson(json, GameConfiguration::class.java)
	
}