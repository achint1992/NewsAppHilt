package com.example.newsapphilt.presentation.di

import com.example.newsapphilt.data.repository.NewsRepositoryImpl
import com.example.newsapphilt.data.repository.datasource.LocalDataStore
import com.example.newsapphilt.data.repository.datasource.RemoteDataStore
import com.example.newsapphilt.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideNewsRepository(
        remoteDataStore: RemoteDataStore,
        localDataStore: LocalDataStore
    ): NewsRepository {
        return NewsRepositoryImpl(remoteDataStore, localDataStore)
    }
}