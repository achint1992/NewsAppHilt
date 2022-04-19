package com.example.newsapphilt.presentation.di

import com.example.newsapphilt.domain.repository.NewsRepository
import com.example.newsapphilt.domain.usecase.GetNewsFromRemote
import com.example.newsapphilt.domain.usecase.SearchNewsFromRemote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetNewsFromRemote(newsRepository: NewsRepository): GetNewsFromRemote {
        return GetNewsFromRemote(newsRepository)
    }

    @Singleton
    @Provides
    fun provideSearchNewsFromRemote(newsRepository: NewsRepository): SearchNewsFromRemote {
        return SearchNewsFromRemote(newsRepository)
    }

}