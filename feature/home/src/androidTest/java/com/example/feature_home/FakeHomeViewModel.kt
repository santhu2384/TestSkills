package com.example.feature_home

import androidx.lifecycle.ViewModel
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import repository.HomeRepository
import usecase.GetLiveMatchesUsecase

class FakeHomeViewModel(initial: HomeUiState) : IHomeViewModel
{
    private val _matches = MutableStateFlow(HomeUiState())
    override var matches: StateFlow<HomeUiState> = _matches

    fun setState(newState: HomeUiState) {
        _matches.value = newState
    }
}