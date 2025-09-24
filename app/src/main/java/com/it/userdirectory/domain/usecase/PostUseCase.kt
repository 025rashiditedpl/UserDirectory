package com.it.userdirectory.domain.usecase

import com.it.userdirectory.domain.repository.PostRepository

class PostUseCase(private val repository: PostRepository) {
    operator fun invoke(userId: Int?)=repository.getPosts(userId)
}