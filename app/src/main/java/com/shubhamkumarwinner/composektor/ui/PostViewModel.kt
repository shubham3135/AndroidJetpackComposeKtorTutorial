package com.shubhamkumarwinner.composektor.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shubhamkumarwinner.composektor.data.domain.use_case.PostUseCase
import com.shubhamkumarwinner.composektor.util.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val useCase: PostUseCase
) : ViewModel() {
    private val _state = mutableStateOf<PostListState>(PostListState())
    val state: State<PostListState> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        useCase().onEach { result ->
            when(result){
                is ApiState.Success -> {
                    _state.value = PostListState(posts = result.data ?: emptyList())
                }
                is ApiState.Error -> {
                    _state.value = PostListState(error = result.message ?: "An unexpected error occurred")
                }
                is ApiState.Loading -> {
                    _state.value = PostListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}