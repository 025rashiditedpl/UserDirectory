package com.it.userdirectory.data.mapper

import com.it.userdirectory.data.model.post.PostResponseItem
import com.it.userdirectory.domain.model.post.Post

fun PostResponseItem.toDomain(): Post{
    return Post(
        body = body.orEmpty(),
        id = id ?: 0,
        title = title.orEmpty(),
        userId = userId ?: 0
    )
}