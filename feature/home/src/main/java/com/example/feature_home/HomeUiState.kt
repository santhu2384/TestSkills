package com.example.feature_home

import com.example.domain.model.Video

data class HomeUiState(
    val isloading:Boolean = false,
    val sections:List<Pair<String,List<Video>>> = emptyList(),
    val error:String? = null
)
