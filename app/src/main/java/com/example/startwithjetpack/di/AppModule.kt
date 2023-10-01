package com.example.startwithjetpack.di

import android.app.Application
import androidx.room.Room
import com.example.startwithjetpack.data.local.NewsDao
import com.example.startwithjetpack.data.local.NewsDataBase
import com.example.startwithjetpack.data.local.NewsTypeConvertor
import com.example.startwithjetpack.data.manager.LocalUserManagerImpl
import com.example.startwithjetpack.data.repository.NewsRepositoryIml
import com.example.startwithjetpack.domain.manager.LocalUserManager
import com.example.startwithjetpack.domain.repository.NewsRepository
import com.example.startwithjetpack.domain.useCases.appEntry.AppEntryUseCases
import com.example.startwithjetpack.domain.useCases.appEntry.GetAppEntry
import com.example.startwithjetpack.domain.useCases.appEntry.SaveAppEntry
import com.example.startwithjetpack.domain.useCases.news.SearchNewsUseCase
import com.example.startwithjetpack.domain.useCases.news.TopHeadLinesNewsUseCase
import com.yasser.networklayer.restAPIs.NetworkLayer
import com.yasser.networklayer.restAPIs.provider.retrofit.RetrofitNetworkProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

     @Provides
     @Singleton
     fun provideLocalManager(
         context: Application
     ):LocalUserManager = LocalUserManagerImpl(context)


    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        userManager:LocalUserManager
    ): AppEntryUseCases =
        AppEntryUseCases(
           GetAppEntry(userManager),
            SaveAppEntry(userManager)
        )
    @Provides
    @Singleton
    fun providesNetworkProvider(
    ): RetrofitNetworkProvider {
        return RetrofitNetworkProvider()
    }
    @Provides
    @Singleton
    fun networkProvider(retrofitProvider: RetrofitNetworkProvider): NetworkLayer {
        return NetworkLayer(retrofitProvider)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(networkLayer: NetworkLayer):NewsRepository = NewsRepositoryIml(networkLayer)

    @Provides
    @Singleton
    fun provideTopHeadLineNewsUseCase(newsRepository: NewsRepository) = TopHeadLinesNewsUseCase(newsRepository)

    @Provides
    @Singleton
    fun provideSearchNewsUseCase(newsRepository: NewsRepository) = SearchNewsUseCase(newsRepository)

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ):NewsDao{
        val room = Room.databaseBuilder(
            application,
            NewsDataBase::class.java,
            "news_db"
        )
            .addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()

        return room.newsDao
    }


}