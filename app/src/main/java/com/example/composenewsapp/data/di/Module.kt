package com.example.composenewsapp.data.di

import android.app.Application
import com.example.composenewsapp.data.manager.LocalStorageManagerImp
import com.example.composenewsapp.data.remote.dto.NewsApi
import com.example.composenewsapp.data.repository.NewsRepositoryImp
import com.example.composenewsapp.domain.repository.NewRepository
import com.example.composenewsapp.manager.LocalStorageManager
import com.example.composenewsapp.manager.usecases.AppEntryUserCases
import com.example.composenewsapp.manager.usecases.ReadAppEntry
import com.example.composenewsapp.manager.usecases.SaveAppEntry
import com.example.composenewsapp.manager.usecases.news.GetNews
import com.example.composenewsapp.manager.usecases.news.NewsUseCases
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
      api: NewsApi
    ):NewRepository = NewsRepositoryImp(
       newsApi = api
    )


    @Provides
    @Singleton
    fun providesNewsUseCases(
        newRepository: NewRepository
    ):NewsUseCases{
        return NewsUseCases(
            news = GetNews(newRepository)
        )
    }

}

