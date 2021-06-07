package com.sample.interactor

import com.sample.data.CredentialsRepository
import com.sample.data.UserRepository
import com.sample.domain.Result
import javax.inject.Inject

class IsUserLoggedInInteractor @Inject constructor(
    private val credentialsRepository: CredentialsRepository,
    private val userRepository: UserRepository
) {

    suspend fun isUserLoggedIn(): Boolean {
        return when (val result = credentialsRepository.getCredentials()) {
            is Result.Failure -> false
            is Result.Success -> userRepository.areCredentialsValid(result.data)
        }
    }

}