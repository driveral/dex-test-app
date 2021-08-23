package com.sample.data

import arrow.core.Either
import com.sample.domain.Credentials
import com.sample.domain.ErrorEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserService @Inject constructor(private val userDataSource: UserDataSource) {
    suspend fun login(userName: String, password: String): Either<ErrorEntity, Credentials> =
        userDataSource.login(userName, password)

    suspend fun logout() {
        userDataSource.logout()
    }

    suspend fun register(userName: String, password: String): Either<ErrorEntity, Unit> =
        userDataSource.register(userName, password)

    suspend fun areCredentialsValid(credentials: Credentials): Boolean =
        userDataSource.areCredentialsValid(credentials)
}