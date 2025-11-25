package com.example.feature_player

import android.content.Context
import android.util.Log
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.domain.model.ManifestType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class PlayerManager(private val context: Context)
{
    private var _player: ExoPlayer? = null
    val player: ExoPlayer?
        get() = _player

    private var listenersAttached = false
    // Expose simple state flows so ViewModel/Compose can observe
    private val _playbackState = MutableStateFlow(Player.STATE_IDLE)
    val playbackState = _playbackState.asStateFlow()

    private val _isBuffering = MutableStateFlow(false)
    val isBuffering = _isBuffering.asStateFlow()

    fun createPlayerIfNeeded()
    {
        if (_player == null) {
            _player = ExoPlayer.Builder(context).build()
            attachListeners()
        }
    }


    fun attachListeners() {

        if (_player == null || listenersAttached) return

        listenersAttached = true

        _player?.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                _playbackState.value = playbackState
                _isBuffering.value = (playbackState == Player.STATE_BUFFERING)
                Log.d("PlayerManager", "onPlaybackStateChanged: $playbackState")
            }

            override fun onIsPlayingChanged(isPlaying: Boolean) {
                Log.d("PlayerManager", "onIsPlayingChanged: $isPlaying")
            }

            override fun onPlayerError(error: PlaybackException) {
                Log.e("PlayerManager", "Player error: ${error.message}")
            }
        })
    }

    fun preparePlayer(uri:String,manifestype: ManifestType,seekPosition: Long = 0L, playWhenReady: Boolean = true)
    {
        createPlayerIfNeeded()

        val mediatype = when(manifestype)
        {
            ManifestType.HLS -> MimeTypes.APPLICATION_M3U8
            ManifestType.DASH -> MimeTypes.APPLICATION_MPD
        }
        val mediaitem = MediaItem.Builder()
            .setUri(uri)
            .setMimeType(mediatype)
            .build()


        _player?.setMediaItem(mediaitem)
        _player?.prepare()
        if (seekPosition > 0L) {
            seekTo(seekPosition)
        }
        _player?.playWhenReady = playWhenReady

    }

    fun seekTo(position: Long) {
        _player?.seekTo(position)
    }

    fun release()
    {
        _player?.release()
        _player = null
        listenersAttached = false
        _playbackState.value = Player.STATE_IDLE
        _isBuffering.value = false
        Log.d("PlayerManager", "Released ExoPlayer")
    }

}