package com.example.newsapp.presentatino.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.newsapp.data.remot.Article

@Composable
fun DetailScreen(
    article: Article,
    modifier: Modifier = Modifier
) {
    Text(article.title)
}