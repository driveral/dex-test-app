package com.sample.framework

import com.sample.data.UserDataSource
import com.sample.domain.Credentials
import com.sample.domain.ErrorEntity
import com.sample.domain.Result
import kotlinx.coroutines.delay
import kotlin.random.Random

class UserDataSourceImpl : UserDataSource {
    override suspend fun login(userName: String, password: String): Result<Credentials> {
        // Fake server delay
        delay(Random.nextInt(from = 1, until = 5) * 1000L)

        if (userName == "admin" && password == "admin") {
            return Result.Success(Credentials(0, "token"))
        }

        return Result.Failure(ErrorEntity.WRONG_CREDENTIALS)
    }

    override suspend fun logout() {
        // Notify the server or something
    }

    override suspend fun areCredentialsValid(credentials: Credentials): Boolean {
        return true
    }
}