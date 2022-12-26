package com.anafthdev.snakeclassic.data.repository

import com.anafthdev.snakeclassic.data.datasource.local.LocalDatasource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
	
	@Provides
	@Singleton
	fun provideRepository(
		localDatasource: LocalDatasource
	): Repository = Repository(
		localDatasource = localDatasource
	)
	
}