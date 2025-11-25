package com.example.data.mapper.repository

import com.example.network.ApiService
import kotlinx.coroutines.flow.Flow
import com.example.domain.model.Match
import repository.MatchRepository


class MatchRepositoryImpl(private val api: ApiService): MatchRepository {
    override fun getLiveMatches(): Flow<List<Match>>
    {
        TODO("Not yet implemented")
    }
}