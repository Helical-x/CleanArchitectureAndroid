package com.example.ceibatest.domain.model

import com.example.ceibatest.data.database.entities.PostEntity

data class Post(
    val userId: Int,
    val title: String,
    val body: String
)


fun PostEntity.toDomain() = Post(userId, title, body)