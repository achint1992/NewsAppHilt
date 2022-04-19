package com.example.newsapphilt.presentation.di

import android.app.Application
import androidx.room.Room
import com.example.newsapphilt.data.db.NewsDao
import com.example.newsapphilt.data.db.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideNewsDatabase(application: Application): NewsDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            NewsDatabase::class.java,
            "newshilt_db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideNewsDao(newsDatabase: NewsDatabase): NewsDao {
        return newsDatabase.getNewsDao()
    }

}