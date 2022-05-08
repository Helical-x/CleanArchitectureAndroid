package com.example.ceibatest.presentation.user_list

import com.example.ceibatest.domain.model.User

data class UserListState(
    val isLoading: Boolean = false,
    val user: List<User> = emptyList(),
    val error: String = ""
)
