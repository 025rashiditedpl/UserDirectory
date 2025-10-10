package com.it.userdirectory.data.repository

import com.it.userdirectory.data.datasource.UsersDataSource
import com.it.userdirectory.data.model.users.UsersResponseItem
import com.it.userdirectory.domain.network.NetworkResult
import com.it.userdirectory.domain.repository.UserRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class UserRepositoryImpl  @Inject constructor(private val dataSource: UsersDataSource) : UserRepository {
    override fun getUsers(): Flow<NetworkResult<List<UsersResponseItem>>> = flow {
        emit(NetworkResult.Loading())
        try {
            val result = dataSource.getUsers()
            emit(NetworkResult.Success(result))
        } catch (e: IOException) {
            emit(NetworkResult.Error("Network error. Please check your connection.${e.message}"))
        } catch (e: HttpException) {
            emit(NetworkResult.Error("Server error: ${e.code()}"))
        } catch (e: Exception) {
            emit(NetworkResult.Error(e.localizedMessage ?: "Unexpected error occurred"))
        }
    }
}