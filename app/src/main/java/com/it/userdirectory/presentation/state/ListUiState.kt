package com.it.userdirectory.presentation.state

import com.it.userdirectory.domain.model.user.User


sealed class ListUiState {
    data object Loading : ListUiState()
    data class Success(val data: List<User?>?) : ListUiState()
    data class Error(val message: String) : ListUiState()
}