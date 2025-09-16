package com.example.composenewsapp.presentation.details

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composenewsapp.data.local.ArticlesEntity
import com.example.composenewsapp.manager.usecases.news.NewsUseCases
import com.example.composenewsapp.utils.UIComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
):ViewModel(){
    var sideEffect by mutableStateOf<String?>(null)
        private set



//    fun onEvent(event: DetailEvent){
//        when(event){
//            is DetailEvent.UpsertDeleteArticle ->{
//
//                viewModelScope.launch {
//                    try{
//                    val article = newsUseCases.selectArticle(event.article.url)
//                    if (article == null){
//                        upsertArticle(event.article)
//                    }
//                    else{
//                        deleteArticle(event.article)
//                    }
//
//                }
//                    catch (e:Exception){
//                        Log.d("--err", "onEvent: ${e.message}")
//                    }
//                }
//            }
//
//            is DetailEvent.RemoveSideEffect ->{
//                sideEffect = null
//            }
//        }
//
//
//    }


    // In your ViewModel:
    fun onEvent(event: DetailEvent) {
        when(event) {
            is DetailEvent.UpsertDeleteArticle -> {
                val executor = Executors.newSingleThreadExecutor()

                executor.execute {
                    try {
                        val article = newsUseCases.selectArticle(event.article.url).get()

                        if (article == null) {
                            // Insert article
                            newsUseCases.upsertArticle(event.article)
                            Handler(Looper.getMainLooper()).post {
                                Log.d("--artUpsert", "onEvent: upserted")
                                sideEffect = "Article Saved"
                            }
                        } else {
                            // Delete article
                            newsUseCases.deleteArticle(event.article)
                            Handler(Looper.getMainLooper()).post {
                                Log.d("--artDelete", "onEvent: deleted")
                                sideEffect = "Article Deleted"
                            }
                        }
                    } catch (e: Exception) {
                        Log.e("--error", "Database operation failed: ${e.message}")
                    } finally {
                        executor.shutdown()
                    }
                }
            }
            is DetailEvent.RemoveSideEffect -> {
                sideEffect = null
            }
        }
    }

    private  fun upsertArticle(article: ArticlesEntity) {
        newsUseCases.upsertArticle(articlesEntity = article)
       // sideEffect = UIComponent.Toast("Article Saved")


    }
    private  fun deleteArticle(article: ArticlesEntity) {
        newsUseCases.deleteArticle(articlesEntity = article)
       // sideEffect = UIComponent.Toast("Article Deleted")
    }

}