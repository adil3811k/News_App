package com.example.newsapp.presentatino.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.paging.compose.LazyPagingItems
import com.example.newsapp.data.local.SearchHistory
import com.example.newsapp.data.remot.Article
import com.example.newsapp.presentatino.Dimension
import com.example.newsapp.presentatino.screens.component.ArticleList
import com.example.newsapp.presentatino.screens.component.NewSearchBar
import com.example.newsapp.presentatino.screens.component.ShimmerEffect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    articals: LazyPagingItems<Article>,
    list: List<SearchHistory>,
    onSearch:(String) -> Unit,
    onSearchDelete:(Int)-> Unit,
    onArticleClick:(Article)-> Unit
) {
    Column{
        NewSearchBar(Modifier , {onSearch(it)},list
        ) {
            onSearchDelete(it)
        }
        ArticleList(articals) {
            onArticleClick(it)
        }
    }
}