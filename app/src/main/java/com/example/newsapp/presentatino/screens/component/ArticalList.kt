package com.example.newsapp.presentatino.screens.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.newsapp.data.remot.Article
import com.example.newsapp.presentatino.Dimension

@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
     articles: LazyPagingItems<Article>,
    onClick:(Article)-> Unit
) {
    val handelPagingResult  = handelPagingResult(articles)
    if (handelPagingResult){
        LazyColumn(
            Modifier.fillMaxSize().padding(horizontal = Dimension.MEDIUM_PADDING),
            verticalArrangement = Arrangement.spacedBy(Dimension.MEDIUM_PADDING),
            contentPadding = PaddingValues(Dimension.EXTRS_SMALL_PADDING)
        ) {
            items(count = articles.itemCount){
                articles[it]?.let{
                    ArticleCard(it, { onClick(it) })
                }
            }
        }
    }
}

@Composable
private fun handelPagingResult(
    articles: LazyPagingItems<Article>,
    modifier: Modifier = Modifier) : Boolean{
    val loadState = articles.loadState
    val error = when{
        loadState.refresh is LoadState.Error->loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error->loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error->loadState.append as LoadState.Error
        else -> null
    }
    return when{
        loadState.refresh is LoadState.Loading->{
            ShimmerEffect()
            false
        }
        error !=null->{
            Box(
                Modifier.fillMaxSize(),
                Alignment.Center
            ){
                Log.d("Remote", "${error.error}")
                Text("Something went wrong ${error.error}")
            }
            false
        }
        else->{
            true
        }
    }
}