package com.example.newsapp.presentatino.screens.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.newsapp.presentatino.Dimension
import com.example.newsapp.R
import com.example.newsapp.data.remot.Article
import com.example.newsapp.presentatino.Dimension.SMALL_PADDING
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun ArticleCard(
    article: Article,
    onClick:(Article)-> Unit,
    modifier: Modifier = Modifier,
) {
    Row (
        Modifier
            .fillMaxWidth()
            .clickable { onClick(article) }
    ){
        AsyncImage(
            placeholder = painterResource(R.drawable.splash_icon),
            model = if(article.urlToImage!=null) article.urlToImage else R.drawable.splash_icon,
            contentDescription = "Image",
            modifier = modifier
                .padding(end = SMALL_PADDING)
                .clip(MaterialTheme.shapes.medium)
                .size(Dimension.IMAGE_SIZE),
            contentScale = ContentScale.Crop
        )
        Column (verticalArrangement = Arrangement.Center){
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Normal),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = article.source.name,
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Normal),
                maxLines = 1,
            )
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomStart){
                Text(
                    text = formatIsoDataTime(article.publishedAt),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight =  FontWeight.Normal),
                )
            }
        }
    }
}
private fun formatIsoDataTime(dateTime: String): String{
    return try {
        val instant = Instant.parse(dateTime)
        val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a")
            .withZone(ZoneId.systemDefault())
        formatter.format(instant)
    }catch (e: Exception){
        e.message?:""
    }
}

