package com.example.newsapp.presentatino.screens

import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WebViewScreen(
    url: String
    ) {
    AndroidView(
        factory = {context->
            WebView(context).apply {
                loadUrl(url)
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}