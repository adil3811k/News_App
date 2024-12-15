package com.example.newsapp.presentatino.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.newsapp.data.remot.Article
import com.example.newsapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    article: Article,
    onBackPress:()-> Unit,
    onWebView:(String)-> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text(article.source.name)},
                navigationIcon = { IconButton(onBackPress) { Icon(Icons.Filled.KeyboardArrowLeft, null) } },
                actions = {
                    IconButton({onWebView(article.url)}) { Icon(painterResource(R.drawable.internet), null)}
                }
            )
        }
    ){paddingValue->
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .padding(paddingValue)
                .padding(horizontal = 8.dp)
                .verticalScroll(scrollState)
        ) {
            AsyncImage(
                model = if(article.urlToImage!=null) article.urlToImage else R.drawable.splash_icon,
                placeholder = painterResource(R.drawable.splash_icon),
                contentDescription = null,
                contentScale = ContentScale.Fit
                )
            Text(
                text = article.title,
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = article.source.name
            )
            Text(
                text = article.content?:"No Data Available",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}