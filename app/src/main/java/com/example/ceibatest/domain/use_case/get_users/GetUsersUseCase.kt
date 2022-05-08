package com.example.ceibatest.domain.use_case.get_users

import android.util.Log
import com.example.ceibatest.common.Resource
import com.example.ceibatest.data.database.entities.toDatabase
import com.example.ceibatest.data.remote.dto.toPost
import com.example.ceibatest.data.remote.dto.toUser
import com.example.ceibatest.data.repository.UserRepository
import com.example.ceibatest.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(): Flow<Resource<List<User>>> = flow {
        try {
            val users = repository.getUsersFromDatabase()
            if (users.isNotEmpty()) {
                emit(Resource.Success(users))
            } else {
                emit(Resource.Loading())
                val usersApi = repository.getUsersFromApi().map { it.toUser() }
                val postsApi = repository.getPostsFromApi().map { it.toPost() }
                repository.insertUsers(usersApi.map { it.toDatabase() })
                repository.insertPosts(postsApi.map { it.toDatabase() })
                emit(Resource.Success(usersApi))
            }
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Ha ocurrido un error"))
        }
    }
}