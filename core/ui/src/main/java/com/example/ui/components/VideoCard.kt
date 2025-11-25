package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.core_common.DeviceType
import com.example.ui.models.VideoUiModel

@Composable
fun VideoCard(video: VideoUiModel, modifier: Modifier = Modifier, onClick:(VideoUiModel) -> Unit)
{
    val cardWidth = if (DeviceType.isTV(LocalContext.current)) 220.dp else 160.dp
    val cardHeight = if (DeviceType.isTV(LocalContext.current)) 140.dp else 100.dp



    Box(modifier = modifier
        .width(cardWidth)
        .height(cardHeight).focusable()
        .clickable { onClick(video) })
    {
       AsyncImage(model = video.thumbnailUrl , contentDescription =video.title,
                  contentScale = ContentScale.Crop, modifier = Modifier.matchParentSize())
        Box(modifier = Modifier
            .matchParentSize()
            .background(
                Brush.verticalGradient(
                    0f to Color.Transparent,
                    1f to Color.Black.copy(alpha = 0.6f)
                )
            ))

        Column(modifier = Modifier
            .align(alignment = Alignment.BottomStart)
            .padding(8.dp))
        {
             if(video.isLive)
             {
                 LiveTag()
             }
            else
             {
                 Text(text = video.title,
                     style = MaterialTheme.typography.bodyMedium,
                 color = Color.White,
                     maxLines = 2
                 )
             }
        }

    }
}