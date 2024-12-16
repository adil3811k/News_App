package com.example.newsapp.presentatino.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.data.remot.Article
import com.example.newsapp.presentatino.screens.component.ArticleList
import com.example.newsapp.presentatino.viewModel.MainViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    search: String,
    mainViewModel: MainViewModel,
    onItemClick:(Article)->Unit
){
    val articles =mainViewModel.searchNews(search).collectAsLazyPagingItems()
    Scaffold (
        topBar = { TopAppBar(title = { Text("Result of $search") }) }
    ){padding->
        ArticleList(
            Modifier.padding(padding) ,
            articles
        ) {onItemClick(it) }
    }
}