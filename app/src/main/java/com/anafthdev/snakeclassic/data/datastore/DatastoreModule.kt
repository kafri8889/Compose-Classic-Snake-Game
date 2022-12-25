package com.anafthdev.snakeclassic.data.datastore

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatastoreModule {
	
	@Provides
	@Singleton
	fun provideAppDataStore(
		@ApplicationContext context: Context
	): AppDatastore = AppDatastore(context)
	
}