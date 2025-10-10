package com.it.userdirectory.domain.usecase

import com.it.userdirectory.data.mapper.toDomain
import com.it.userdirectory.domain.model.user.User
import com.it.userdirectory.domain.network.NetworkResult
import com.it.userdirectory.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserUseCase(private val repository: UserRepository) {
    operator fun invoke(): Flow<NetworkResult<List<User>>> {
        return repository.getUsers().map { result ->
            when (result) {
                is NetworkResult.Success -> NetworkResult.Success(result.data.map { it.toDomain() })
                is NetworkResult.Error -> NetworkResult.Error(result.message)
                is NetworkResult.Loading -> NetworkResult.Loading()
            }
        }
    }
}