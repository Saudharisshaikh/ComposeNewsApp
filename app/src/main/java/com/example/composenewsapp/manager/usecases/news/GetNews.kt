package com.example.composenewsapp.manager.usecases.news

import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.paging.PagingData
import com.example.composenewsapp.domain.model.Article
import com.example.composenewsapp.domain.repository.NewRepository
import kotlinx.coroutines.flow.Flow

class GetNews(private val newsRepository: NewRepository) {

    operator fun invoke(sources:List<String>): Flow<PagingData<Article>> {
        return  newsRepository.getNews(source = sources)
    }
}