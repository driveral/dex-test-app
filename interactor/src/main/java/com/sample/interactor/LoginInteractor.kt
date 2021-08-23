package com.sample.interactor

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.sample.data.CredentialsRepository
import com.sample.data.UserService
import com.sample.domain.ErrorEntity
import javax.inject.Inject

class LoginInteractor @Inject constructor(
    private val userService: UserService,
    private val credentialsRepository: CredentialsRepository
) {

    suspend fun login(user: String, pass: String): Either<ErrorEntity, Unit> {
        return when (val loginResult = userService.login(user, pass)) {
            is Either.Left -> loginResult.value.left()
            is Either.Right -> {
                credentialsRepository.saveCredentials(loginResult.value)
                Unit.right()
            }
        }
    }
}

