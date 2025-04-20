package com.example.composenewsapp.data.remote.dto

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.composenewsapp.domain.model.Article

class NewPagingSource(private  val newsApi: NewsApi,
                      private  val source:String
    ):PagingSource<Int,Article>() {

    private var totalNewCount:Int = 0

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {

   return  state.anchorPosition?.let {
       anchorPosition ->
       val acnhorPage = state.closestPageToPosition(anchorPosition)
       acnhorPage?.prevKey?.plus(1)?:acnhorPage?.nextKey?.minus(1)
   }

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key?:1
        return try {
            val newsResponse = newsApi.getNews(sources = source, page = page)
            totalNewCount += newsResponse.articles.size
            val articles = newsResponse.articles.distinctBy { it.title }
            LoadResult.Page(
                data = articles,
                nextKey = if(totalNewCount == newsResponse.totalResults) null else page + 1,
                prevKey = null
            )
        }
        catch(e:Exception){
         e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }

    }
}