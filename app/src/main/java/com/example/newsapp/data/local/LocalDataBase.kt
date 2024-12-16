package com.example.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.data.remot.Article
import com.example.newsapp.domain.MyCustomTypeConverter

@Database(entities = [Article::class, SearchHistory::class], version = 1)
@TypeConverters(MyCustomTypeConverter::class)
abstract class LocalDataBase : RoomDatabase(){
    abstract fun getDao(): RoomDao
}