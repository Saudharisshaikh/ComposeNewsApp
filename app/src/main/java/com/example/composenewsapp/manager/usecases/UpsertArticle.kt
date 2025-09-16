package com.example.composenewsapp.manager.usecases

import android.util.Log
import com.example.composenewsapp.data.local.ArticlesDao
import com.example.composenewsapp.data.local.ArticlesEntity
import com.example.composenewsapp.domain.repository.NewRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future


class UpsertArticle(
    private val newsRepository: NewRepository
) {
    private val executor = Executors.newSingleThreadExecutor()

    operator fun invoke(articlesEntity: ArticlesEntity): Future<Boolean> {
        return executor.submit<Boolean> {
            try {
                newsRepository.upsertArticle(article = articlesEntity)
                //articlesDao.upsert(articlesEntity)
                true
            } catch (e: Exception) {
                Log.d("--exp:", "invoke: ${e.message}")
                false
            }
        }
    }

    // Add a shutdown method if needed
    fun shutdown() {
        executor.shutdown()
    }
}