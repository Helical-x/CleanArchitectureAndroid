package com.example.ceibatest.presentation.user_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ceibatest.common.Resource
import com.example.ceibatest.domain.use_case.get_user.GetUserUseCase
import com.example.ceibatest.domain.use_case.get_user_posts.GetUserPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getUserPostsUseCase: GetUserPostsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(UserDetailState())
    private val _state2 = mutableStateOf(PostDetailState())
    val state: State<UserDetailState> get() = _state
    val state2: State<PostDetailState> get() = _state2

    private val userId = savedStateHandle.get<String>("userId")


    init {
        userId?.let { getUser(it.toInt()) }
        userId?.let { getPosts(it.toInt()) }
    }

    private fun getUser(id: Int) {
        getUserUseCase(id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = UserDetailState(user = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = UserDetailState(error = result.message ?: "Ha ocurrido un error")
                }
                is Resource.Loading -> {
                    _state.value = UserDetailState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)

    }

    private fun getPosts(id: Int) {
        getUserPostsUseCase(id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state2.value = PostDetailState(post = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state2.value =
                        PostDetailState(error = result.message ?: "Ha ocurrido un error")
                }
                is Resource.Loading -> {
                    _state2.value = PostDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}