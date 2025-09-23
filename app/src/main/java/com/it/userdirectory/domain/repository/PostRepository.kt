package com.it.userdirectory.domain.repository

import com.it.userdirectory.domain.model.post.PostResponseItem
import com.it.userdirectory.domain.model.users.UsersResponseItem
import com.it.userdirectory.domain.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    fun getPosts(userId: String?): Flow<NetworkResult<List<PostResponseItem>>>
}