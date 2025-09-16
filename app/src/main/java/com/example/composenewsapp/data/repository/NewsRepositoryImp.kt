package com.example.composenewsapp.data.repository

import androidx.paging.Config
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSourceFactory
import com.example.composenewsapp.data.local.ArticlesDao
import com.example.composenewsapp.data.local.ArticlesEntity
import com.example.composenewsapp.data.remote.dto.NewPagingSource
import com.example.composenewsapp.data.remote.dto.NewResponse
import com.example.composenewsapp.data.remote.dto.NewsApi
import com.example.composenewsapp.data.remote.dto.SearchPagingSource
import com.example.composenewsapp.domain.model.Article
import com.example.composenewsapp.domain.repository.NewRepository
import com.example.e_pharmacycompose.presentation.onboarding.pages
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class NewsRepositoryImp(
    private val newsApi: NewsApi,
    private val newsDao: ArticlesDao

) :NewRepository{
    override fun getNews(source: List<String>): Flow<PagingData<Article>> {

        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewPagingSource(
                    newsApi = newsApi,
                    source = source.joinToString(separator = ",")

                )
            }
        ).flow
    }

    override fun searchNews(searchNews: String, source: List<String>): Flow<PagingData<Article>> {

        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchPagingSource(
                    searchQuery = searchNews,
                    newsApi = newsApi,
                    source = source.joinToString(separator = ",")

                )
            }
        ).flow
    }

    override fun upsertArticle(article: ArticlesEntity) {
         newsDao.upsert(articlesEntity = article)
    }

    override fun deleteArticle(article: ArticlesEntity) {

        newsDao.delete(articlesEntity = article)
    }

    override fun selectArticles(): Flow<List<ArticlesEntity>> {
        return newsDao.getArticles().onEach { it.reversed() }
    }

    override fun selectArticle(url: String): ArticlesEntity? {
        return newsDao.getArticle(url = url)
    }


}