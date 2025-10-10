package com.it.userdirectory.presentation.state

import com.it.userdirectory.data.model.post.PostResponseItem
import com.it.userdirectory.domain.model.post.Post

sealed class DetailUiState {

    data object Loading : DetailUiState()
    data class Success(val data: List<Post?>?) : DetailUiState()
    data class Error(val message: String) : DetailUiState()
}