package com.example.ui.models

enum class ManifestType {HLS, DASH}
data class VideoUiModel(
    val id:String,
    val title:String,
    val thumbnailUrl:String,
    val isLive: Boolean = false,
    val metadata:String = "",
    /*val manifesturl:String,
    val manifestType: ManifestType*/
)
