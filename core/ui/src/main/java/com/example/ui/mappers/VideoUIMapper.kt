package com.example.ui.mappers

import com.example.ui.models.VideoUiModel
import com.example.domain.model.Video

fun Video.toUiModel():VideoUiModel
{
    return VideoUiModel(
        id = id,
        title = title,
        thumbnailUrl = thumbnailurl,
        isLive = isLive
    )
}
