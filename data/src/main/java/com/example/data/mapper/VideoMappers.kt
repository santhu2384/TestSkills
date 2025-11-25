package com.example.data.mapper

import com.example.data.mapper.dto.VideoDTO
import com.example.domain.model.Video

fun VideoDTO.toDomain(): Video {
    return Video(
        id = id,
        title=title,
        thumbnailurl = thumbnailurl,
        isLive = isLive,
        manifestUrl = manifestUrl,
        manifestType = manifestType
    )
}