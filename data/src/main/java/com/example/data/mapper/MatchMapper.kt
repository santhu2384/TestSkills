package com.example.data.mapper

import com.example.domain.model.Match
import com.example.data.mapper.remote.dto.MatchDto

fun MatchDto.toDomain() = Match(
    id = id,
    teamA = teamA,
    teamB = teamB,
    startTime = startTime
)
