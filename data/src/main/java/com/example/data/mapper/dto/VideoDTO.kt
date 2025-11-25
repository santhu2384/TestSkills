package com.example.data.mapper.dto

import com.example.domain.model.ManifestType

data class VideoDTO(
    val id:String,val title:String,val thumbnailurl:String,val isLive:Boolean,val metadata:String = "",
    val manifestUrl:String,val manifestType: ManifestType
)
