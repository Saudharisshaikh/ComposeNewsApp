package com.example.composenewsapp.manager.usecases

import com.example.composenewsapp.data.local.ArticlesDao
import com.example.composenewsapp.data.local.ArticlesEntity
import com.example.composenewsapp.domain.repository.NewRepository
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

class SelectArticle(
    private val
    newsRepository: NewRepository,
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()
) {
    operator fun invoke(url: String): Future<ArticlesEntity?> {
        return executor.submit<ArticlesEntity?> {
            newsRepository.selectArticle(url = url)
           // articlesDao.getArticle(url)
        }
    }
}

