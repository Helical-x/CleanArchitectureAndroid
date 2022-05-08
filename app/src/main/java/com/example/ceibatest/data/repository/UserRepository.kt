package com.example.ceibatest.data.repository

import com.example.ceibatest.data.database.dao.PostDao
import com.example.ceibatest.data.database.dao.UserDao
import com.example.ceibatest.data.database.entities.PostEntity
import com.example.ceibatest.data.database.entities.UserEntity
import com.example.ceibatest.data.remote.UserApi
import com.example.ceibatest.data.remote.dto.PostDto
import com.example.ceibatest.data.remote.dto.UserDto
import com.example.ceibatest.domain.model.Post
import com.example.ceibatest.domain.model.User
import com.example.ceibatest.domain.model.toDomain
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val UserApi: UserApi,
    private val userDao: UserDao,
    private val postDao: PostDao
) {
    //API FUNCTIONS
    suspend fun getUsersFromApi(): List<UserDto> {
        return UserApi.getUsers()
    }

    suspend fun getPostsFromApi(): List<PostDto> {
        return UserApi.getPosts()
    }

    //DATABASE FUNCTIONS
    suspend fun getUsersFromDatabase(): List<User> {
        val response: List<UserEntity> = userDao.getAllUsers()
        return response.map { it.toDomain() }
    }

    suspend fun getUserFromDatabase(id: Int): List<User> {
        val response: List<UserEntity> = userDao.getUser(id)
        return response.map { it.toDomain() }
    }

    suspend fun insertUsers(users: List<UserEntity>) {
        userDao.insertAll(users)
    }

    suspend fun insertPosts(posts: List<PostEntity>) {
        postDao.insertAll(posts)
    }

    suspend fun getUserPosts(id: Int): List<Post> {
        val response: List<PostEntity> = postDao.getUserPosts(id)
        return response.map { it.toDomain() }
    }

}