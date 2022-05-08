package com.example.ceibatest.domain.use_case.get_user_posts

import android.util.Log
import com.example.ceibatest.common.Resource
import com.example.ceibatest.data.repository.UserRepository
import com.example.ceibatest.domain.model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetUserPostsUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(id: Int): Flow<Resource<List<Post>>> = flow {
        try {
            emit(Resource.Loading())

            val post = repository.getUserPosts(id)
            emit(Resource.Success(post))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Ha ocurrido un error"))
        }
    }
}