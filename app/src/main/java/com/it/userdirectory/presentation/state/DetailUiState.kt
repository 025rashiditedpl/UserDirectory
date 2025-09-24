package com.it.userdirectory.presentation.state

import com.it.userdirectory.domain.model.post.PostResponseItem

sealed class DetailUiState {

    data object Loading : DetailUiState()
    data class Success(val data: List<PostResponseItem?>?) : DetailUiState()
    data class Error(val message: String) : DetailUiState()
}