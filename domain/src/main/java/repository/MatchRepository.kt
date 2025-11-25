package repository

import kotlinx.coroutines.flow.Flow
import com.example.domain.model.Match

interface MatchRepository {
     fun getLiveMatches():Flow<List<Match>>
}