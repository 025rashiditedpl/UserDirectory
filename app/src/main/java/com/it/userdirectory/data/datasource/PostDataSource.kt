package com.it.userdirectory.data.datasource

import com.it.userdirectory.data.model.post.PostResponseItem
import com.it.userdirectory.domain.network.NetworkUrlProvider.POSTS
import retrofit2.http.GET
import retrofit2.http.Query

interface PostDataSource {
    @GET(POSTS)
    suspend fun getPosts(
        @Query("userId")userId: Int?,
    ) : List<PostResponseItem>
}