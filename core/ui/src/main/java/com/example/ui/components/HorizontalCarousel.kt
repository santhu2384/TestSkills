package com.example.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ui.models.VideoUiModel

@Composable
fun HorizontalCarousel(items:List<VideoUiModel>, modifier: Modifier = Modifier, onitemclick:(VideoUiModel) -> Unit)
{
    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier, contentPadding = PaddingValues(horizontal = 16.dp))
    {
        items(items){ video ->
            VideoCard(video = video, onClick = onitemclick)
        }
    }
    
}