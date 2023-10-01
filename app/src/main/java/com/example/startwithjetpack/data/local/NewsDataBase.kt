package com.example.startwithjetpack.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.startwithjetpack.domain.model.Article

@Database(entities = [Article::class], version = 2)
@TypeConverters(NewsTypeConvertor::class)
abstract class NewsDataBase:RoomDatabase() {

    abstract val newsDao:NewsDao
}