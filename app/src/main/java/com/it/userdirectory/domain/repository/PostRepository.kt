package com.it.userdirectory.domain.repository

import com.it.userdirectory.data.model.post.PostResponseItem
import com.it.userdirectory.domain.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    fun getPosts(userId: Int?): Flow<NetworkResult<List<PostResponseItem>>>
}