package com.sample.interactor

import arrow.core.Either
import arrow.core.left
import com.sample.data.CredentialsRepository
import com.sample.data.PostRepository
import com.sample.domain.ErrorEntity
import com.sample.domain.Post
import javax.inject.Inject

class GetPostsInteractor @Inject constructor(
    private val credentialsRepository: CredentialsRepository,
    private val postRepository: PostRepository
) {

    suspend fun getPosts(): Either<ErrorEntity, List<Post>> {
        return when (val result = credentialsRepository.getCredentials()) {
            is Either.Left -> result.value.left()
            is Either.Right -> postRepository.getLastestPosts(result.value)
        }
    }
}