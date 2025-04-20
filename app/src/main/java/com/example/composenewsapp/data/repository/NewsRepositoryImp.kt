package com.example.composenewsapp.data.repository

import androidx.paging.Config
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSourceFactory
import com.example.composenewsapp.data.remote.dto.NewPagingSource
import com.example.composenewsapp.data.remote.dto.NewResponse
import com.example.composenewsapp.data.remote.dto.NewsApi
import com.example.composenewsapp.domain.model.Article
import com.example.composenewsapp.domain.repository.NewRepository
import com.example.e_pharmacycompose.presentation.onboarding.pages
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

}