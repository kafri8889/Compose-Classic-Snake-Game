package com.anafthdev.snakeclassic.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.anafthdev.snakeclassic.model.Score

@Database(
	entities = [
		Score::class
	],
	version = 1,
	exportSchema = false
)
@TypeConverters(AppDatabaseTypeConverter::class)
abstract class AppDatabase: RoomDatabase() {
	
	abstract fun scoreReadDao(): ScoreReadDAO
	
	abstract fun scoreWriteDao(): ScoreWriteDAO
	
	companion object {
		private var INSTANCE: AppDatabase? = null
		
		fun getInstance(context: Context): AppDatabase {
			if (INSTANCE == null) {
				synchronized(AppDatabase::class) {
					INSTANCE = Room
						.databaseBuilder(context, AppDatabase::class.java, "score.db")
						.build()
				}
			}
			
			return INSTANCE!!
		}
	}

}