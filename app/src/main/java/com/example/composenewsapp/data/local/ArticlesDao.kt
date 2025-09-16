package com.example.composenewsapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.example.composenewsapp.data.local.ArticlesEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe


@Dao
interface ArticlesDao {

   @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun upsert(articlesEntity: ArticlesEntity)

    @Delete
     fun delete(articlesEntity: ArticlesEntity)

    @Query("SELECT * FROM ArticlesEntity")
    fun getArticles():Flow<List<ArticlesEntity>>

    @Query("SELECT * FROM ArticlesEntity WHERE url =:url")
     fun getArticle(url: String): ArticlesEntity?

}