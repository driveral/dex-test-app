package com.sample.interactor

import com.sample.data.CredentialsRepository
import com.sample.data.UserService
import com.sample.domain.Result
import javax.inject.Inject

class RegisterInteractor @Inject constructor(
    private val userService: UserService,
    private val credentialsRepository: CredentialsRepository
) {

    suspend fun register(user: String, pass: String): Result<Unit> {
        return  userService.register(user, pass)
    }
}