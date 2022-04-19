package com.example.newsapphilt.presentation.di

import android.app.Application
import com.example.newsapphilt.domain.usecase.GetNewsFromRemote
import com.example.newsapphilt.domain.usecase.SearchNewsFromRemote
import com.example.newsapphilt.presentation.news.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelModule {
    @Singleton
    @Provides
    fun provideNewsViewModelFactory(
        application: Application,
        getNewsFromRemote: GetNewsFromRemote,
        searchNewsFromRemote: SearchNewsFromRemote
    ): NewsViewModelFactory {
        return NewsViewModelFactory(application, getNewsFromRemote, searchNewsFromRemote)
    }
}