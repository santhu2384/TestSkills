package com.example.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ui.models.VideoUiModel

@Composable
fun SectionWithCarousel(title:String,items:List<VideoUiModel>,modifier: Modifier = Modifier,onItemclick:(VideoUiModel)->Unit)
{
    SectionTitle(title = title)
    HorizontalCarousel(items = items , modifier,onitemclick = onItemclick)
}