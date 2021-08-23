package com.sample.data

import arrow.core.Either
import com.sample.domain.Credentials
import com.sample.domain.ErrorEntity

interface UserDataSource {
    suspend fun login(userName: String, password: String): Either<ErrorEntity, Credentials>
    suspend fun logout()
    suspend fun areCredentialsValid(credentials: Credentials): Boolean
    suspend fun register(userName: String, password: String): Either<ErrorEntity, Unit>
}

