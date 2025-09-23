package com.it.userdirectory.data.repository

import com.it.userdirectory.data.datasource.UsersDataSource
import com.it.userdirectory.domain.model.users.UsersResponseItem
import com.it.userdirectory.domain.network.NetworkResult
import com.it.userdirectory.domain.repository.UserRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl  @Inject constructor(private val dataSource: UsersDataSource) : UserRepository {
    override fun getUsers(): Flow<NetworkResult<List<UsersResponseItem>>> = flow {
        try {
            emit(NetworkResult.Loading())
            val result=dataSource.getUsers()
            emit(NetworkResult.Success(data = result))
        }catch (e: Exception){
            emit(NetworkResult.Error(message = e.message?:"Error Occurred!"))
        }
    }
}