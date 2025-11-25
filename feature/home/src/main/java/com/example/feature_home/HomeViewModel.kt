package com.example.feature_home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import com.example.domain.model.Match
import com.example.domain.model.Video
import repository.HomeRepository
import usecase.GetLiveMatchesUsecase
import javax.inject.Inject
import com.example.domain.model.common.Result
import kotlinx.coroutines.flow.update

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo:HomeRepository,private val getLiveMatchesUsecase: GetLiveMatchesUsecase) :ViewModel()
{
    private val _matches = MutableStateFlow(HomeUiState())
    var matches:StateFlow<HomeUiState> = _matches
    init {
        loadHomeSections()
    }

    /*fun loadMatches()
    {
        viewModelScope.launch {
            getLiveMatchesUsecase().collect{_matches.value = it}
        }
    }*/

    fun loadHomeSections()
    {

        _matches.value = _matches.value.copy(isloading = true,error=null)


        viewModelScope.launch {
            when (val result = safeCall {repo.getHomeSections() })
            {
                is Result.Success -> {
                    _matches.value = _matches.value.copy(sections = result.data, isloading = false, error = null)
                }
                is Result.Error ->{
                    _matches.value = _matches.value.copy(isloading = false, error = result.message ?: "Unknown Error")
                }
                else -> Unit
            }
        }
    }

    private suspend fun <T> safeCall(action: suspend () -> T) :Result<T>
    {
       return try {
             return Result.success(action())
        }catch (e:Exception)
        {
          Result.error(e.message.toString())
        }
    }
}