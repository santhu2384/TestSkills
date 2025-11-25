package com.example.domain.model

enum class ManifestType{HLS,DASH}

data class Video(
    val id:String,val title:String,val thumbnailurl:String,val isLive:Boolean,val metadata:String = "",
    val manifestUrl:String,val manifestType: ManifestType
)
