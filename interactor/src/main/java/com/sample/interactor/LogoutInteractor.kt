package com.sample.interactor

import com.sample.data.CredentialsRepository
import com.sample.data.UserRepository
import javax.inject.Inject

class LogoutInteractor @Inject constructor(
    private val userRepository: UserRepository,
    private val credentialsRepository: CredentialsRepository
) {

    suspend fun logout() {
        userRepository.logout()
        credentialsRepository.deleteCredentials()
    }
}