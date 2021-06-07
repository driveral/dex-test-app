package com.sample.interactor

import com.sample.data.CredentialsRepository
import com.sample.data.PostRepository
import com.sample.domain.Post
import com.sample.domain.Result
import javax.inject.Inject

class GetPostsInteractor @Inject constructor(
    private val credentialsRepository: CredentialsRepository,
    private val postRepository: PostRepository
) {

    suspend fun getPosts(): Result<List<Post>> {
        return when (val result = credentialsRepository.getCredentials()) {
            is Result.Failure -> Result.Failure(result.error)
            is Result.Success -> postRepository.getLastestPosts(result.data)
        }
    }
}