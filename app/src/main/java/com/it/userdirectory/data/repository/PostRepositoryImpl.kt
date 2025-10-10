package com.it.userdirectory.data.repository

import com.it.userdirectory.data.datasource.PostDataSource
import com.it.userdirectory.data.model.post.PostResponseItem
import com.it.userdirectory.domain.network.NetworkResult
import com.it.userdirectory.domain.repository.PostRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class PostRepositoryImpl  @Inject constructor(private val dataSource: PostDataSource) : PostRepository {
    override fun getPosts(userId: Int?): Flow<NetworkResult<List<PostResponseItem>>> = flow{
        emit(NetworkResult.Loading())
        try {
            val result = dataSource.getPosts(userId)
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