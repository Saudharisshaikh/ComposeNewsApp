package com.example.composenewsapp.manager.usecases

import com.example.composenewsapp.data.local.ArticlesDao
import com.example.composenewsapp.data.local.ArticlesEntity

class UpsertArticle(
  private val articlesDao: ArticlesDao) {

    suspend operator fun invoke(articlesEntity: ArticlesEntity){
        articlesDao.upsert(articlesEntity)
    }
}