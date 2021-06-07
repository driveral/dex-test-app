package com.sample.data

import com.sample.domain.Credentials
import com.sample.domain.Post
import com.sample.domain.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepository @Inject constructor(private val postDataSource: PostDataSource) {
    suspend fun getLastestPosts(credentials: Credentials): Result<List<Post>> =
        postDataSource.getLastestPosts(credentials)
}