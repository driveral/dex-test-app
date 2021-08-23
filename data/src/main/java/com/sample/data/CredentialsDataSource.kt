package com.sample.data

import arrow.core.Either
import com.sample.domain.Credentials
import com.sample.domain.ErrorEntity

interface CredentialsDataSource {
    suspend fun saveCredentials(credentials: Credentials)
    fun getCredentials(): Either<ErrorEntity, Credentials>
    fun deleteCredentials()
}

