package com.sample.interactor

import com.sample.data.CredentialsRepository
import com.sample.data.UserService
import javax.inject.Inject

class LogoutInteractor @Inject constructor(
    private val userService: UserService,
    private val credentialsRepository: CredentialsRepository
) {

    suspend fun logout() {
        userService.logout()
        credentialsRepository.deleteCredentials()
    }
}