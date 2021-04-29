package com.sample.data

import com.sample.domain.Credentials
import com.sample.domain.Post
import com.sample.domain.Result

interface PostDataSource {
    suspend fun getLastestPosts(credentials: Credentials): Result<List<Post>>
}

