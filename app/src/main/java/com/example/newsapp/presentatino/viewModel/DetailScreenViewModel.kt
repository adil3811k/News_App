package com.example.newsapp.presentatino.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.remot.Article
import com.example.newsapp.domain.LocalNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val localNewsRepository: LocalNewsRepository
) : ViewModel(){
    private val _IsFavorite = MutableStateFlow(false)
    val IsFavorite  = _IsFavorite.asStateFlow()

    fun toggleFavorite(article: Article){
        if (!_IsFavorite.value){
            // to list
            try {
                viewModelScope.launch{
                    localNewsRepository.insertArticle(article)
                    _IsFavorite.value = true
                }
            }catch (e: Exception){
                Log.d("RoomDataBase", "${e.message}")
            }
        }else{
            // Remove for list
            try {
                viewModelScope.launch{
                    val article = localNewsRepository.getArticle(article.title)
                    localNewsRepository.deleteArticle(article)
                    _IsFavorite.value =false
                }
            }catch (e: Exception){
             Log.d("RoomDataBase", "${e.message}")
            }
        }
    }

    fun updateState(title: String){
        viewModelScope.launch{
            _IsFavorite.value= localNewsRepository.isArticleFavorite(title)
        }
    }
}