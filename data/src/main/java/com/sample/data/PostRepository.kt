package com.sample.data

import arrow.core.Either
import com.sample.domain.Credentials
import com.sample.domain.ErrorEntity
import com.sample.domain.Post
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepository @Inject constructor(private val postDataSource: PostDataSource) {
    suspend fun getLastestPosts(credentials: Credentials): Either<ErrorEntity, List<Post>> =
        postDataSource.getLastestPosts(credentials)
}