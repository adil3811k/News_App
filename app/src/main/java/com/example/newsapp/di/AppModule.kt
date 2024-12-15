package com.example.newsapp.di

import android.content.Context
import androidx.room.Room
import com.example.newsapp.data.local.LocalDataBase
import com.example.newsapp.data.local.LocalRepositoryImp
import com.example.newsapp.data.local.RoomDao
import com.example.newsapp.data.remot.Article
import com.example.newsapp.data.remot.dao.NewsApi
import com.example.newsapp.data.remot.repository.RemoteNewsRepositoryImp
import com.example.newsapp.domain.LocalNewsRepository
import com.example.newsapp.domain.RemoteNewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    // Room implementation
    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext context: Context): RoomDao{
        return Room.databaseBuilder(context , LocalDataBase::class.java, "My_DataBase")
            .build()
            .getDao()
    }

    // LocalRepositoryImplementation
    @Provides
    fun proviewLocalRepositoryImplementation(roomDao: RoomDao): LocalNewsRepository{
        return LocalRepositoryImp(roomDao)
    }

    // retrofit implementation
    @Provides
    @Singleton
    fun provideNewsApi() : NewsApi {
        val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
     return   Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(NewsApi::class.java)
    }
    @Provides
    @Singleton
    fun provideRepository(newsApi: NewsApi): RemoteNewsRepository = RemoteNewsRepositoryImp(newsApi)
}