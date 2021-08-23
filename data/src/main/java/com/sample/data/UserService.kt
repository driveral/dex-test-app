package com.sample.data

import com.sample.domain.Credentials
import com.sample.domain.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserService @Inject constructor(private val userDataSource: UserDataSource) {
    suspend fun login(userName: String, password: String): Result<Credentials> =
        userDataSource.login(userName, password)

    suspend fun logout() {
        userDataSource.logout()
    }

    suspend fun register(userName: String, password: String): Result<Unit> =
        userDataSource.register(userName, password)

    suspend fun areCredentialsValid(credentials: Credentials): Boolean = userDataSource.areCredentialsValid(credentials)
}