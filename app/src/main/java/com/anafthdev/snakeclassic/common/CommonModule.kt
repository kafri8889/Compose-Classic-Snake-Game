package com.anafthdev.snakeclassic.common

import com.anafthdev.snakeclassic.data.datastore.AppDatastore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CommonModule {
	
	@Provides
	@Singleton
	fun provideGameConfiguration(
		appDatastore: AppDatastore
	): GameConfiguration = GameConfiguration(appDatastore)
	
}