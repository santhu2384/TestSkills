package com.example.domain

import com.example.domain.model.Match
import com.example.testing.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import repository.HomeRepository
import repository.MatchRepository
import usecase.GetLiveMatchesUsecase

class GetLiveMatchesUseCaseTest
{
    @get:Rule
    val dispatcher = MainDispatcherRule()

    val repo = mockk<MatchRepository>()
    val fakeflow = flow<List<Match>>{
        listOf(Match("1","X","Y","10:AM")) }

   lateinit var useCase: GetLiveMatchesUsecase

   @Before
   fun setup()
   {
       useCase = GetLiveMatchesUsecase(repo)
   }

    @Test
    fun `GetliveMatches fetches data from repo`() = runTest{

        coEvery { repo.getLiveMatches() } returns fakeflow
        val result = useCase()

        assertEquals(result,fakeflow)
        coVerify( exactly = 1) {repo.getLiveMatches() }

    }
}