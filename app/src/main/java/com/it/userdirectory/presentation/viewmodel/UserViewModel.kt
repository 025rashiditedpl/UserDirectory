package com.it.userdirectory.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.it.userdirectory.domain.network.NetworkResult
import com.it.userdirectory.domain.usecase.UserUseCase
import com.it.userdirectory.presentation.state.ListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class UserViewModel  @Inject constructor (private val useCase: UserUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<ListUiState>(ListUiState.Loading)
    val uiState = _uiState.asStateFlow()


    fun getUserList() {
        viewModelScope.launch {
            useCase.invoke().collect { result ->
                _uiState.value = when (result) {
                    is NetworkResult.Loading -> ListUiState.Loading
                    is NetworkResult.Success -> ListUiState.Success(result.data)
                    is NetworkResult.Error -> ListUiState.Error(result.message)
                }
            }
        }
    }
}