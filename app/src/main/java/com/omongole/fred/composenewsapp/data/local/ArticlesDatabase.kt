package com.omongole.fred.composenewsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.omongole.fred.composenewsapp.data.modal.Article

@Database( entities = [Article::class], version = 2, exportSchema = false )
@TypeConverters(ArticlesTypeConverter::class)
abstract class ArticlesDatabase : RoomDatabase() {
    abstract val getDao : ArticlesDao
}