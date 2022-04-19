package com.example.newsapphilt.presentation.di

import com.example.newsapphilt.BuildConfig
import com.example.newsapphilt.data.api.NewsService
import com.example.newsapphilt.data.db.NewsDao
import com.example.newsapphilt.data.repository.datasource.LocalDataStore
import com.example.newsapphilt.data.repository.datasource.RemoteDataStore
import com.example.newsapphilt.data.repository.datasourceimpl.LocalDataStoreImpl
import com.example.newsapphilt.data.repository.datasourceimpl.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun provideRemoteNewsDataSource(newsService: NewsService): RemoteDataStore {
        return RemoteDataSourceImpl(newsService, BuildConfig.API_KEY)
    }

    @Singleton
    @Provides
    fun provideLocalNewsDataSource(newsDao: NewsDao): LocalDataStore {
        return LocalDataStoreImpl(newsDao)
    }
}