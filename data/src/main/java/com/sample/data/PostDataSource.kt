package com.sample.data

import arrow.core.Either
import com.sample.domain.Credentials
import com.sample.domain.ErrorEntity
import com.sample.domain.Post

interface PostDataSource {
    suspend fun getLastestPosts(credentials: Credentials): Either<ErrorEntity, List<Post>>
}

