package com.example.network

import kotlinx.coroutines.flow.Flow
import com.example.data.mapper.remote.dto.MatchDto
import retrofit2.http.GET

interface ApiService
{
    @GET("live/matches")
    fun getLiveMatches():Flow<List<MatchDto>>
}