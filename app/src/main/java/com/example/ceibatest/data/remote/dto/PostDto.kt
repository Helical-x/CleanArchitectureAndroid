package com.example.ceibatest.data.remote.dto

import com.example.ceibatest.domain.model.Post

data class PostDto(
    val userId: Int,
    val title: String,
    val body: String
)

fun PostDto.toPost(): Post {
    return Post(
        userId = userId,
        title = title,
        body = body
    )
}
