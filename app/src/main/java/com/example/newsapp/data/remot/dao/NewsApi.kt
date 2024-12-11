package com.example.newsapp.data.remot.dao

import com.example.newsapp.data.remot.NewsResponce
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getHeadline(
        @Query("page") page: Int,
        @Header("X-Api-Key") apiKey : String = "7d660f2a6b554a709356bb42ca6c0239", // this is valid APi Key I Test on postman
        @Query("country") country: String = "US",
    ): NewsResponce

    @GET("everything")
    suspend fun getSearchResult(
        @Query("q")q: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey : String = "7d660f2a6b554a709356bb42ca6c0239", // this is valid APi Key I Test on postman
    ): NewsResponce
}