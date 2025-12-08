package com.example.feature_player

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.domain.model.ManifestType
import com.example.domain.model.Video
import com.example.ui.models.VideoUiModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.MatcherAssert
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import repository.HomeRepository
import java.util.Locale

class PlayerViewModelTest
{
    @get:Rule
    val testDispatcher = MainDispatcherRule()


    private val repo = mockk<HomeRepository>()
    private lateinit var vm:PlayerViewModel
    private val context = mockk<Context>(relaxed = true)
    private val playermanager = mockk<PlayerManager>()
    val mockPlayer = mockk<ExoPlayer>(relaxed = true)


    // Fake Video Response
    private val fakeVideo = Video(
        id = "123",
        title = "Sample",
        isLive = false,
        metadata = "",
        thumbnailurl = "",
        manifestUrl = "https://test.com/hls.m3u8",
        manifestType = ManifestType.HLS
    )



    fun createVm()
    {
        vm = PlayerViewModel(repo,context)
    }



    @Test
    fun `setVideo updates videoid and loads video`() = runTest {
        coEvery { repo.findVideoById("123") } returns fakeVideo

        createVm()
        vm.setVideo("123")
        advanceUntilIdle()

        //assertThat(vm.videoId,)

        assertThat(vm.videoId).isEqualTo("123")
        assertThat(vm.uistate.value.video).isEqualTo(fakeVideo)


    }

    @Test
    fun `loadVideo updates UI state with video when videoId is valid`() = runTest {
        coEvery { repo.findVideoById("123") } returns fakeVideo

        createVm()
        vm.videoId = "123"
        vm.loadVideo()
        advanceUntilIdle()

        assertThat(vm.uistate.value.video).isEqualTo(fakeVideo)
    }

    @Test
    fun `loadVideo does not update UI state when videoId is invalid`() = runTest {
        coEvery { repo.findVideoById("invalid") } returns null

        createVm()
        vm.videoId = "invalid"
        vm.loadVideo()
        advanceUntilIdle()

        assertThat(vm.uistate.value.video).isNull()
    }

    @Test
    fun `loadVideo does not crash when repository throws exception`() = runTest {
        coEvery { repo.findVideoById("123") } throws RuntimeException("Repository error")

        createVm()
        vm.videoId = "123"
        vm.loadVideo()
        advanceUntilIdle()

        assertThat(vm.uistate.value.video).isNull()
    }

    @Test
    fun `Saveplaypostion stores current player state` ()= runTest {

        // 1. Mock child Player
        // 2. Inject into manager
        every { playermanager.player } returns mockPlayer
        //mockPlayer.c


        every { mockPlayer.currentPosition } returns 5000L
        every { mockPlayer.playWhenReady } returns false

        // Create the ViewModel after mocking dependencies
        vm = PlayerViewModel(repo, context)
        vm.playerManager = playermanager

        vm.Saveplaypostion()
        assertThat(vm.savedPosition).isEqualTo(5000L)
        assertThat(vm.savedWhenReady).isEqualTo(false)


    }

    @Test
    fun `restorePlayerState resets play position to last state` () = runTest{

        every { playermanager.player } returns mockPlayer

        vm = PlayerViewModel(repo, context)
        vm.playerManager = playermanager
        vm.savedPosition = 3000L
        vm.savedWhenReady = true
        vm.restorePlayerState()

        verify { mockPlayer.seekTo(3000L) }
        verify { mockPlayer.playWhenReady = true }
    }


    @Test
    fun `preparePlayback prepares player when ready`() = runTest {
        // Create the ViewModel after mocking dependencies
        every { playermanager.preparePlayer(any(), any(), any(), any()) } returns Unit

        vm = PlayerViewModel(repo, context)
        vm.playerManager = playermanager

        vm._uistate.value = PlayerUiModel(fakeVideo)
        vm.savedPosition = 2000L
        vm.savedWhenReady = true

        vm.preparePlayback()

        verify { playermanager.preparePlayer(fakeVideo.manifestUrl,fakeVideo.manifestType,2000L,true) }
    }


    /*@Test
    fun `onCleared release playermanager while exit`()
    {
        val vm = PlayerViewModel(repo, context)
        val onClearedMethod = PlayerViewModel::class.java.getDeclaredMethod("onCleared")
        onClearedMethod.isAccessible = true

        onClearedMethod.invoke(vm)

        verify { playermanager.release() }
    }*/

}

