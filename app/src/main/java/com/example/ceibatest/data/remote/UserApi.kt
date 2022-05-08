package com.example.ceibatest.data.remote

import com.example.ceibatest.data.remote.dto.PostDto
import com.example.ceibatest.data.remote.dto.UserDto
import retrofit2.http.GET

interface UserApi {
    @GET("/users")
    suspend fun getUsers(): List<UserDto>

    @GET("/posts")
    suspend fun getPosts(): List<PostDto>
}