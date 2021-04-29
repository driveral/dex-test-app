package com.sample.data

import com.sample.domain.Credentials
import com.sample.domain.Result

interface CredentialsDataSource {
    suspend fun saveCredentials(credentials: Credentials)
    fun getCredentials(): Result<Credentials>
    fun deleteCredentials()
}

