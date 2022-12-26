package com.anafthdev.snakeclassic.data.datasource

import android.content.Context
import com.anafthdev.snakeclassic.data.datasource.local.AppDatabase
import com.anafthdev.snakeclassic.data.datasource.local.LocalDatasource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatasourceModule {
	
	@Provides
	@Singleton
	fun provideAppDatabase(
		@ApplicationContext context: Context
	): AppDatabase = AppDatabase.getInstance(context)
	
	@Provides
	@Singleton
	fun provideLocalDatasource(
		appDatabase: AppDatabase
	): LocalDatasource = LocalDatasource(
		scoreReadDAO = appDatabase.scoreReadDao(),
		scoreWriteDAO = appDatabase.scoreWriteDao()
	)
	
}