package com.example.newsapp.presentatino.screens.component

import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import com.example.newsapp.R
import com.example.newsapp.presentatino.Dimension


@Composable
fun ShimmerEffect(
    no: Int =12,
    modifier: Modifier = Modifier
) {
    for (i in 0..no){
        ShimmerEffectItem(modifier.padding(bottom = Dimension.MEDIUM_PADDING))
    }
}

@Composable
private fun ShimmerEffectItem(modifier: Modifier = Modifier) {
    Row {
        Box(
            modifier = modifier
                .padding(end = Dimension.SMALL_PADDING)
                .size(Dimension.IMAGE_SIZE)
                .clip(MaterialTheme.shapes.medium)
                .ShimmerEffect()
        )
        Column {
            Box(
                modifier
                    .fillMaxWidth()
                    .height(Dimension.IMAGE_SIZE)
                    .clip(MaterialTheme.shapes.medium)
                    .ShimmerEffect())
        }
    }
}

@Composable
private fun  Modifier.ShimmerEffect() = composed{
    val transition  = rememberInfiniteTransition(label = "")
    val alph = transition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.9f,
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    ).value
    background(color = colorResource(R.color.shimmer).copy(alpha =  alph))
}

