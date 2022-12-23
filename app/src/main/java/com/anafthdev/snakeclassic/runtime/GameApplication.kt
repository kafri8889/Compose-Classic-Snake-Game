package com.anafthdev.snakeclassic.runtime

import android.app.Application
import com.anafthdev.snakeclassic.BuildConfig
import timber.log.Timber

class GameApplication: Application() {
	
	override fun onCreate() {
		super.onCreate()
		if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
	}
}