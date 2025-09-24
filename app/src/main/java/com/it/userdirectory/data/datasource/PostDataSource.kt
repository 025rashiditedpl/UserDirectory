package com.it.userdirectory.data.datasource

import com.it.userdirectory.domain.model.post.PostResponseItem
import com.it.userdirectory.domain.model.users.UsersResponseItem
import com.it.userdirectory.domain.network.NetworkUrlProvider.POSTS
import com.it.userdirectory.domain.network.NetworkUrlProvider.USERS
import retrofit2.http.GET
import retrofit2.http.Query

interface PostDataSource {
    @GET(POSTS)
    suspend fun getPosts(
        @Query("userId")userId: Int?,
    ) : List<PostResponseItem>
}