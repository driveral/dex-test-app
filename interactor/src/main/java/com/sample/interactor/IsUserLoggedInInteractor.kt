package com.sample.interactor

import arrow.core.Either
import com.sample.data.CredentialsRepository
import com.sample.data.UserService
import javax.inject.Inject

class IsUserLoggedInInteractor @Inject constructor(
    private val credentialsRepository: CredentialsRepository,
    private val userService: UserService
) {

    suspend fun isUserLoggedIn(): Boolean {
        return when (val result = credentialsRepository.getCredentials()) {
            is Either.Left -> false
            is Either.Right -> userService.areCredentialsValid(result.value)
        }
    }

}