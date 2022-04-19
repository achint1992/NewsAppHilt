package com.example.newsapphilt.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapphilt.data.model.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun getNewsDao(): NewsDao
}