package com.sample.data

import arrow.core.Either
import com.sample.domain.Credentials
import com.sample.domain.ErrorEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CredentialsRepository @Inject constructor(private val credentialsDataSource: CredentialsDataSource) {
    suspend fun saveCredentials(credentials: Credentials) {
        credentialsDataSource.saveCredentials(credentials)
    }

    fun getCredentials(): Either<ErrorEntity, Credentials> = credentialsDataSource.getCredentials()

    fun deleteCredentials() {
        credentialsDataSource.deleteCredentials()
    }
}