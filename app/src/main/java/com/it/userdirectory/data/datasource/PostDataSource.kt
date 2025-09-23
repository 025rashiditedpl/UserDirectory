package com.it.userdirectory.data.datasource

import com.it.userdirectory.domain.model.post.PostResponseItem
import com.it.userdirectory.domain.model.users.UsersResponseItem
import com.it.userdirectory.domain.network.NetworkUrlProvider.USERS
import retrofit2.http.GET
import retrofit2.http.Query

interface PostDataSource {
    @GET(USERS)
    suspend fun getPosts(
        @Query("userId")userId:String?,
    ) : List<PostResponseItem>
}