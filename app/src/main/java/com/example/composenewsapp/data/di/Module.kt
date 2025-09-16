package com.example.composenewsapp.data.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.composenewsapp.data.local.ArticlesDao
import com.example.composenewsapp.data.local.NewArticleDatabase
import com.example.composenewsapp.data.manager.LocalStorageManagerImp
import com.example.composenewsapp.data.remote.dto.NewsApi
import com.example.composenewsapp.data.repository.NewsRepositoryImp
import com.example.composenewsapp.domain.repository.NewRepository
import com.example.composenewsapp.manager.LocalStorageManager
import com.example.composenewsapp.manager.usecases.AppEntryUserCases
import com.example.composenewsapp.manager.usecases.DeleteArticle
import com.example.composenewsapp.manager.usecases.ReadAppEntry
import com.example.composenewsapp.manager.usecases.SaveAppEntry
import com.example.composenewsapp.manager.usecases.SelectArticle
import com.example.composenewsapp.manager.usecases.SelectedArticle
import com.example.composenewsapp.manager.usecases.UpsertArticle
import com.example.composenewsapp.manager.usecases.news.GetNews
import com.example.composenewsapp.manager.usecases.news.NewsUseCases
import com.example.composenewsapp.manager.usecases.news.SearchNews
import com.example.e_pharmacycompose.utils.Constants
import com.example.e_pharmacycompose.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalStorageManager = LocalStorageManagerImp(application)


    @Provides
    @Singleton
    fun providesAppEntryUseCases(localStorageManager: LocalStorageManager)
    = AppEntryUserCases(readAppEntry = ReadAppEntry(localStorageManager),
        saveAppEntry = SaveAppEntry(localStorageManager)
    )



    @Provides
    @Singleton
    fun providesNewsApi() :NewsApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)

    }

    @Provides
    @Singleton
    fun providesNewsRepository(
      api: NewsApi,
      articlesDao: ArticlesDao
    ):NewRepository = NewsRepositoryImp(
       newsApi = api,
       newsDao = articlesDao
    )


    @Provides
    @Singleton
    fun providesNewsUseCases(
        newRepository: NewRepository,
        articlesDao: ArticlesDao

    ):NewsUseCases{
        return NewsUseCases(
            news = GetNews(newRepository),
            searchNews = SearchNews(newRepository),
            upsertArticle = UpsertArticle(newRepository),
            deleteArticle = DeleteArticle(newRepository),
            selectedArticle = SelectedArticle(newRepository),
            selectArticle = SelectArticle(newRepository)
        )
    }

@Provides
@Singleton
fun providesArticleDatabase(application: Application):NewArticleDatabase{
    return Room.databaseBuilder(context = application,
        klass = NewArticleDatabase::class.java,
        name = Constants.DATABASE_NAME) .fallbackToDestructiveMigration()
        .build()
}

  @Provides
  @Singleton
  fun providesArticleDao(newArticleDatabase: NewArticleDatabase):
          ArticlesDao = newArticleDatabase.articlesDao


}



