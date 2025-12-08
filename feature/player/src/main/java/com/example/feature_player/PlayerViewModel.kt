package com.example.feature_player

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Video
import com.example.ui.models.VideoUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import repository.HomeRepository
import javax.inject.Inject

data class PlayerUiModel(val video: Video?=null)

@HiltViewModel
class PlayerViewModel @Inject constructor(private val repo:HomeRepository,
                                          @ApplicationContext context:Context):ViewModel()
{
    val _uistate = MutableStateFlow(PlayerUiModel())
    val uistate:StateFlow<PlayerUiModel> = _uistate

    //creating playermanger from viem model to survie config changes
    var playerManager:PlayerManager = PlayerManager(context)
    val player get() = playerManager.player

    //to retain position from where it stopped
    var savedPosition : Long = 0L
    var savedWhenReady : Boolean = true
    var videoId:String = ""

    fun setVideo(id: String) {
        videoId = id
        loadVideo()
    }

    fun Saveplaypostion()
    {
        playerManager.player?.let { player ->
            savedPosition = player.currentPosition
            savedWhenReady = player.playWhenReady
        }
    }

    fun restorePlayerState()
    {
        playerManager.player?.let { player ->
            player.seekTo(savedPosition)
            player.playWhenReady = savedWhenReady
        }

    }

    fun preparePlayback() {
        val video = _uistate.value.video ?: return
        playerManager.preparePlayer(video.manifestUrl, video.manifestType,savedPosition,playWhenReady = savedWhenReady)
    }

   /* init {
        loadVideo()
    }*/

    fun loadVideo()
    {
        viewModelScope.launch {
            try {

                val video = repo.findVideoById(videoId)
                _uistate.value = PlayerUiModel(video)
            }catch (e:Exception)
            {
                _uistate.value = PlayerUiModel(null)
            }
        }
    }

     override fun onCleared() {
        super.onCleared()
        playerManager.release()
    }

}
