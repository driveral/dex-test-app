package com.sample.data

import com.sample.domain.Credentials
import com.sample.domain.Result

interface UserDataSource {
    suspend fun login(userName: String, password: String): Result<Credentials>
    suspend fun logout()
    suspend fun areCredentialsValid(credentials: Credentials): Boolean
}

