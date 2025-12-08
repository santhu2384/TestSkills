package com.example.feature_home

import app.cash.turbine.test
import com.example.domain.model.ManifestType
import com.example.domain.model.Video
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import repository.HomeRepository
import usecase.GetLiveMatchesUsecase

class HomeViewModelTest
{
    @get:Rule
    val dispatcher = MainDispatcherRule()

    private val repo = mockk<HomeRepository>()
    private val usecase = mockk<GetLiveMatchesUsecase>()

    lateinit var vm:HomeViewModel




    val video = listOf( Video("123","","",false,"","",ManifestType.HLS))
    val sections:List<Pair<String,List<Video>>> = listOf(Pair("Section1",video))

    @Before
    fun setup()
    {
        vm = HomeViewModel(repo,usecase)
    }


    @Test
    fun `loadHomeSections loads home catergories` () = runTest {

        coEvery { repo.getHomeSections() } returns sections
        vm.loadHomeSections()



        advanceUntilIdle()
        assertThat(vm.matches.value.isloading).isFalse()
        assertThat(vm.matches.value.sections).isEqualTo(sections)
        assertThat(vm.matches.value.sections.size).isEqualTo(1)
        assertThat(vm.matches.value.error).isNull()

    }

    /*@Test
    fun `loadHomeSections emits initial, loading, success`() = runTest {

        // repo mock
        coEvery { repo.getHomeSections() } returns sections

        vm.matches.test {

            // RECREATE VM inside Turbine so init{} emissions are captured
            vm = HomeViewModel(repo, usecase)

            // 1. Initial state
            val initial = awaitItem()
            assertThat(initial.isloading).isFalse()

            // 2. Loading state
            val loading = awaitItem()
            assertThat(loading.isloading).isTrue()

            // 3. Success state
            val success = awaitItem()
            assertThat(success.sections).isEqualTo(sections)
            assertThat(success.isloading).isFalse()

            cancelAndIgnoreRemainingEvents()
        }
    }*/


}