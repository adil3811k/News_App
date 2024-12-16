package com.example.newsapp.presentatino.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newsapp.data.local.SearchHistory
import com.example.newsapp.data.remot.Article
import com.example.newsapp.domain.LocalNewsRepository
import com.example.newsapp.domain.RemoteNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.cache
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val remoteNewsRepository: RemoteNewsRepository,
    private val localNewsRepository: LocalNewsRepository
): ViewModel() {
    val searchHistory = localNewsRepository.gatAllSearchHistory().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
    )

    fun addToSearchHistory(search: String){
        viewModelScope.launch{
            localNewsRepository.addHistory(search)
        }
    }

    fun deleteSearch(id: Int){
        viewModelScope.launch{
            localNewsRepository.deleteHistory(id)
        }
    }

    val topHeadLine by lazy {   remoteNewsRepository.getTopHeadLine().cachedIn(viewModelScope)}
    fun searchNews(search:String): Flow<PagingData<Article>>{
        return remoteNewsRepository.getSearchResult(search).cachedIn(viewModelScope)
    }
}