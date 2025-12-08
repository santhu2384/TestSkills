package com.example.feature_home

import androidx.compose.runtime.State
import kotlinx.coroutines.flow.StateFlow

interface IHomeViewModel {
    val matches: StateFlow<HomeUiState>
}