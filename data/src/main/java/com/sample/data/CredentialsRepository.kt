package com.sample.data

import com.sample.domain.Credentials
import com.sample.domain.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CredentialsRepository @Inject constructor(private val credentialsDataSource: CredentialsDataSource) {
    suspend fun saveCredentials(credentials: Credentials) {
        credentialsDataSource.saveCredentials(credentials)
    }

    fun getCredentials(): Result<Credentials> = credentialsDataSource.getCredentials()

    fun deleteCredentials() {
        credentialsDataSource.deleteCredentials()
    }
}