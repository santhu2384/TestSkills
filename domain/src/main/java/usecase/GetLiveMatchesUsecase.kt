package usecase

import repository.MatchRepository
import javax.inject.Inject

class GetLiveMatchesUsecase @Inject constructor(private val repo:MatchRepository)
{
    operator fun invoke() = repo.getLiveMatches()
}