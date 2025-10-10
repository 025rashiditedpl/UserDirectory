package com.it.userdirectory.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.it.userdirectory.domain.network.NetworkResult
import com.it.userdirectory.domain.usecase.PostUseCase
import com.it.userdirectory.presentation.state.DetailUiState
import com.it.userdirectory.presentation.state.ListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class PostViewModel @Inject constructor(private val useCase: PostUseCase): ViewModel() {

    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState = _uiState.asStateFlow()


    fun getPostList(userId: Int?) {
        viewModelScope.launch {
            useCase.invoke(userId).collect { result ->
                _uiState.value = when (result) {
                    is NetworkResult.Loading -> DetailUiState.Loading
                    is NetworkResult.Success -> DetailUiState.Success(result.data)
                    is NetworkResult.Error -> DetailUiState.Error(result.message)
                }
            }
        }
    }


}