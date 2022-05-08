package com.example.ceibatest.presentation.user_detail

import com.example.ceibatest.domain.model.Post
import com.example.ceibatest.domain.model.User

data class UserDetailState(
    val isLoading: Boolean = false,
    val user: List<User> = emptyList(),
    val error: String = ""
)

data class PostDetailState(
    val isLoading: Boolean = false,
    val post: List<Post> = emptyList(),
    val error: String = ""
)
