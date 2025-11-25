package com.example.feature_home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.model.Video
import com.example.ui.components.SectionWithCarousel
import com.example.ui.mappers.toUiModel

@Composable
fun HomeScreen(vm: HomeViewModel = hiltViewModel(),OnClickVideo: (String) -> Unit)
{
    val matches = vm.matches.value
    when
    {
        matches.isloading ->{
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
            {
                CircularProgressIndicator()
            }
        }
        matches.error !=null  -> {
            Text(text = "Error is ${matches.error}",color = MaterialTheme.colorScheme.error)

        }
        else ->
        {
            LazyColumn(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp))
            {
                items(matches.sections)
                {
                   match -> SectionWithCarousel(title = match.first ,items = match.second.map { video -> video.toUiModel() },
                    onItemclick = { uimodel  ->OnClickVideo( uimodel.id)})
                }
            }
        }
    }


}

