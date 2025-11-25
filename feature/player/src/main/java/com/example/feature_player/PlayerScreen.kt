package com.example.feature_player

import android.util.Log
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.ui.PlayerView
import com.example.domain.model.Video

@Composable
fun PlayerScreen(videoid:String,viewmodel:PlayerViewModel = hiltViewModel()) {
    //create and remember PlayerManger
    //val playermanger = remember {PlayerManager(context = context)}
    val playermanger = viewmodel.playerManager





    Log.d("Playscreen", "Received videoid is $videoid")

    //Observe state
    val uistate = viewmodel.uistate.collectAsState()

    // observe buffering via playerManager flow (converted to Compose state)
    val isBuffering = playermanger.isBuffering.collectAsState(initial = false)

    // Load the video dynamically when ID changes
    LaunchedEffect(key1 = videoid)
    {
        viewmodel.setVideo(videoid)
    }




    LaunchedEffect(key1 = uistate.value.video)
    {
        uistate.value.video ?: return@LaunchedEffect

            // ensure player exists & prepare
            playermanger.createPlayerIfNeeded()

            viewmodel.preparePlayback()
            //viewmodel.restorePlayerState()

    }


    Box(modifier = Modifier.fillMaxSize())
    {
        uistate.value.video?.let { video ->
            PlayerContent(video = uistate.value.video, playermanger = playermanger)
            if (isBuffering.value) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Black.copy(alpha = 0.4f)),
                    contentAlignment = Alignment.Center,
                )
                {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }



    DisposableEffect(Unit)
    {
        onDispose {
            viewmodel.Saveplaypostion()
        }
    }
}

@Composable
fun PlayerContent(video: Video?,playermanger:PlayerManager)
{
    if(video == null) return
    AndroidView(modifier = Modifier.fillMaxSize(),

        factory = {context ->
            PlayerView(context).apply {
                layoutParams = ViewGroup.LayoutParams(MATCH_PARENT,MATCH_PARENT)
                keepScreenOn = true
                isFocusable = true
                requestFocus()
                player = playermanger.player
                useController = true
            }


        },
        update = {view -> view.player = playermanger.player}
    )
}

