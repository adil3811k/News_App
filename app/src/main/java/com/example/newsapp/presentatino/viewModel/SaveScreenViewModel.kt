package com.example.newsapp.presentatino.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.LocalNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SaveScreenViewModel @Inject constructor(
    localNewsRepository: LocalNewsRepository
): ViewModel() {
    val state = localNewsRepository.getAllFavorites().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),emptyList())
}