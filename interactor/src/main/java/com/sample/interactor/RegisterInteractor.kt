package com.sample.interactor

import arrow.core.Either
import com.sample.data.CredentialsRepository
import com.sample.data.UserService
import com.sample.domain.ErrorEntity
import javax.inject.Inject

class RegisterInteractor @Inject constructor(
    private val userService: UserService,
    private val credentialsRepository: CredentialsRepository
) {

    suspend fun register(user: String, pass: String): Either<ErrorEntity, Unit> {
        return userService.register(user, pass)
    }
}