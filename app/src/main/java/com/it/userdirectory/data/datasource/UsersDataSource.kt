package com.it.userdirectory.data.datasource

import com.it.userdirectory.data.model.users.UsersResponseItem
import com.it.userdirectory.domain.network.NetworkUrlProvider.USERS
import retrofit2.http.GET

interface UsersDataSource {

    @GET(USERS)
    suspend fun getUsers() : List<UsersResponseItem>
}