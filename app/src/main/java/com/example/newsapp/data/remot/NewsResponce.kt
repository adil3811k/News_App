package com.example.newsapp.data.remot

data class NewsResponce(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)