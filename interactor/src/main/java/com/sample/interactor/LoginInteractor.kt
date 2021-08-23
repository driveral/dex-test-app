package com.sample.interactor

import com.sample.data.CredentialsRepository
import com.sample.data.UserService
import com.sample.domain.Result
import javax.inject.Inject

class LoginInteractor @Inject constructor(
    private val userService: UserService,
    private val credentialsRepository: CredentialsRepository
) {

    suspend fun login(user: String, pass: String): Result<Unit> {
        return when (val loginResult = userService.login(user, pass)) {
            is Result.Success -> {
                credentialsRepository.saveCredentials(loginResult.data)
                Result.Success(Unit)
            }
            is Result.Failure -> Result.Failure(loginResult.error)
        }
    }
}

