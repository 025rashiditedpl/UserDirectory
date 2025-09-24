package com.it.userdirectory.presentation.state

import com.it.userdirectory.domain.model.users.UsersResponseItem


sealed class ListUiState {
    data object Loading : ListUiState()
    data class Success(val data: List<UsersResponseItem?>?) : ListUiState()
    data class Error(val message: String) : ListUiState()
}