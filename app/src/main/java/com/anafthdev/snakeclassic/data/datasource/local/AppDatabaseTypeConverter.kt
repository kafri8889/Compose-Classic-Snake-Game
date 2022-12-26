package com.anafthdev.snakeclassic.data.datasource.local

import androidx.room.TypeConverter
import com.anafthdev.snakeclassic.model.GameConfigurationData
import com.google.gson.Gson

object AppDatabaseTypeConverter {
	
	@TypeConverter
	fun gameConfigurationDataToJson(gameConfiguration: GameConfigurationData) = Gson().toJson(gameConfiguration)
	
	@TypeConverter
	fun gameConfigurationDataFromJson(json: String) = Gson().fromJson(json, GameConfigurationData::class.java)
	
}