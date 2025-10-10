package com.it.userdirectory.domain.repository

import com.it.userdirectory.data.model.users.UsersResponseItem
import com.it.userdirectory.domain.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUsers(): Flow<NetworkResult<List<UsersResponseItem>>>
}