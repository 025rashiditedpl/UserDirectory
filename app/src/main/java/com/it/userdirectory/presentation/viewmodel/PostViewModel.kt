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


    fun getPostList(userId: Int?)=viewModelScope.launch {
        try {
            useCase.invoke(userId).collect {result ->
                when(result){

                    is NetworkResult.Loading -> _uiState.value= DetailUiState.Loading
                    is NetworkResult.Success -> _uiState.value= DetailUiState.Success(result.data)
                    is NetworkResult.Error -> _uiState.value= DetailUiState.Error(result.message ?:"Something Went Wrong")
                }
            }
        }catch (e: Exception){
            _uiState.value= DetailUiState.Error(e.message?:"")
        }

    }
}