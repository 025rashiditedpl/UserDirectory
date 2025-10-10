package com.it.userdirectory.domain.usecase

import com.it.userdirectory.data.mapper.toDomain
import com.it.userdirectory.domain.model.post.Post
import com.it.userdirectory.domain.model.user.User
import com.it.userdirectory.domain.network.NetworkResult
import com.it.userdirectory.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PostUseCase(private val repository: PostRepository) {
    operator fun invoke(userId: Int?):Flow<NetworkResult<List<Post>>>{
      return  repository.getPosts(userId).map {result ->
          when(result){
                is NetworkResult.Success -> NetworkResult.Success(result.data.map { it.toDomain() })
                is NetworkResult.Error -> NetworkResult.Error(result.message)
                is NetworkResult.Loading -> NetworkResult.Loading()
          }
      }
    }
}