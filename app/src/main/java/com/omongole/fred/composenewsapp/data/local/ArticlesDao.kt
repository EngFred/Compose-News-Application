package com.omongole.fred.composenewsapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.omongole.fred.composenewsapp.data.modal.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticlesDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArticle( article: Article )

    @Delete
    suspend fun deleteArticle( article: Article )

    @Query("SELECT * FROM article")
    fun getArticles() : Flow<List<Article>>

    @Query("SELECT * FROM article WHERE url =:url ")
    suspend fun getArticle( url: String ) : Article?
}