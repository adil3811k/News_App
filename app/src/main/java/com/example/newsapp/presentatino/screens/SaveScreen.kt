package com.example.newsapp.presentatino.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.newsapp.data.remot.Article
import com.example.newsapp.presentatino.screens.component.ArticleCard
import com.example.newsapp.presentatino.viewModel.SaveScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaveScreen(
    viewModel: SaveScreenViewModel = hiltViewModel(),
    onItemClick:(Article)-> Unit
) {
    Scaffold (
        topBar = { TopAppBar(
            expandedHeight = 40.dp,
            title = { Text("Save News") }
        ) }
    ){padding->
        val state = viewModel.state.collectAsStateWithLifecycle()
        LazyColumn(
            modifier = Modifier.padding(padding).padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(state.value){
                ArticleCard(it,{onItemClick(it)})
            }
        }
    }
}