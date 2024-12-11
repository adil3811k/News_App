package com.example.newsapp.presentatino.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newsapp.data.remot.Article
import com.example.newsapp.domain.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel() {



    val topHeadLine by lazy {   newsRepository.getTopHeadLine().cachedIn(viewModelScope)}
    fun searchNews(search:String): Flow<PagingData<Article>>{
        return newsRepository.getSearchResult(search).cachedIn(viewModelScope)
    }
}