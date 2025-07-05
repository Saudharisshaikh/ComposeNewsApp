package com.example.composenewsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.composenewsapp.data.remote.dto.NewPagingSource
import com.example.composenewsapp.data.remote.dto.NewsApi
import com.example.composenewsapp.data.remote.dto.SearchPagingSource
import com.example.composenewsapp.data.local.Article
import com.example.composenewsapp.domain.repository.NewRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImp(
    private val newsApi: NewsApi
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

}