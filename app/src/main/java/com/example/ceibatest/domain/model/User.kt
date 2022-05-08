package com.example.ceibatest.domain.model

import com.example.ceibatest.data.database.entities.UserEntity

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String
)

fun UserEntity.toDomain() = User(id, name, email, phone)