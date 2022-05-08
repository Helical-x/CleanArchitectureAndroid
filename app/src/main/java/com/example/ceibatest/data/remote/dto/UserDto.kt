package com.example.ceibatest.data.remote.dto

import com.example.ceibatest.domain.model.User

data class UserDto(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String
)

fun UserDto.toUser(): User {
    return User(
        id = id,
        name = name,
        email = email,
        phone = phone
    )
}