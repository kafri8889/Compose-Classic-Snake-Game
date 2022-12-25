package com.anafthdev.snakeclassic.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.anafthdev.snakeclassic.data.Constant
import com.anafthdev.snakeclassic.data.Easing
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppDatastore @Inject constructor(private val context: Context) {
	
	/**
	 * @param size size in dp
	 */
	suspend fun setFloorSize(size: Float) {
		context.datastore.edit { preferences ->
			preferences[floorSize] = size
		}
	}
	
	/**
	 * @param delay in milliseconds
	 */
	suspend fun setMovementDelay(delay: Int) {
		context.datastore.edit { preferences ->
			preferences[movementDelay] = delay
		}
	}
	
	/**
	 * @param easing [Easing]
	 */
	suspend fun setEasingAnimation(easing: Easing) {
		context.datastore.edit { preferences ->
			preferences[easingAnimation] = easing.ordinal
		}
	}
	
	val getFloorSize: Flow<Float> = context.datastore.data.map { preferences ->
		preferences[floorSize] ?: 6f
	}
	
	val getMovementDelay: Flow<Int> = context.datastore.data.map { preferences ->
		preferences[movementDelay] ?: 600
	}
	
	val getEasingAnimation: Flow<Easing> = context.datastore.data.map { preferences ->
		Easing.values()[preferences[easingAnimation] ?: 0]
	}
	
	companion object {
		val Context.datastore: DataStore<Preferences> by preferencesDataStore("app_datastore")
		
		val floorSize = floatPreferencesKey(Constant.PREFERENCES_FLOOR_SIZE)
		val movementDelay = intPreferencesKey(Constant.PREFERENCES_MOVEMENT_DELAY)
		val easingAnimation = intPreferencesKey(Constant.PREFERENCES_EASING_ANIMATION)
	}
	
}