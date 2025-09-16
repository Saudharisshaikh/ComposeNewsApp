package com.example.composenewsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ArticlesEntity::class], version = 2)
abstract class NewArticleDatabase:RoomDatabase() {
abstract val articlesDao: ArticlesDao
}