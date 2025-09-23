package com.it.userdirectory.data.repository

import com.it.userdirectory.data.datasource.PostDataSource
import com.it.userdirectory.domain.model.post.PostResponseItem
import com.it.userdirectory.domain.model.users.UsersResponseItem
import com.it.userdirectory.domain.network.NetworkResult
import com.it.userdirectory.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PostRepositoryImpl(private val dataSource: PostDataSource) : PostRepository {
    override fun getPosts(userId: String?): Flow<NetworkResult<List<PostResponseItem>>> = flow{
        try {
            emit(NetworkResult.Loading())
            val result= dataSource.getPosts(userId)
            emit(NetworkResult.Success(data = result))
        } catch (e: Exception) {
            emit(NetworkResult.Error(message = e.message?:"Error Occurred!"))
        }
    }
}