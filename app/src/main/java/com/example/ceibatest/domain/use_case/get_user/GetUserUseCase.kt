package com.example.ceibatest.domain.use_case.get_user

import com.example.ceibatest.common.Resource
import com.example.ceibatest.data.repository.UserRepository
import com.example.ceibatest.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(id: Int): Flow<Resource<List<User>>> = flow {
        try {
            emit(Resource.Loading())
            val user = repository.getUserFromDatabase(id)
            emit(Resource.Success(user))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Ha ocurrido un error"))
        }
    }
}