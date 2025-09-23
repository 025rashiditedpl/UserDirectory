package com.it.userdirectory.domain.usecase

import com.it.userdirectory.domain.repository.UserRepository

class UserUseCase(private val repository: UserRepository) {
    operator fun invoke()=repository.getUsers()
}