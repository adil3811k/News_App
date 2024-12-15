package com.example.newsapp.presentatino.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newsapp.data.remot.Article
import com.example.newsapp.domain.RemoteNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val remoteNewsRepository: RemoteNewsRepository
): ViewModel() {



    val topHeadLine by lazy {   remoteNewsRepository.getTopHeadLine().cachedIn(viewModelScope)}
    fun searchNews(search:String): Flow<PagingData<Article>>{
        return remoteNewsRepository.getSearchResult(search).cachedIn(viewModelScope)
    }
}