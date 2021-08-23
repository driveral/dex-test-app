package com.sample.framework

import android.content.SharedPreferences
import androidx.core.content.edit
import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.sample.domain.Credentials
import com.sample.domain.ErrorEntity
import javax.inject.Inject

const val USER_KEY = "USER_KEY"
const val TOKEN_KEY = "TOKEN_KEY"

class CredentialsDataSourceImpl @Inject constructor(private val sharedPreferences: SharedPreferences) :
    com.sample.data.CredentialsDataSource {

    override suspend fun saveCredentials(credentials: Credentials) {
        sharedPreferences.edit {
            putString(USER_KEY, credentials.userId)
            putString(TOKEN_KEY, credentials.token)
        }
    }

    override fun getCredentials(): Either<ErrorEntity, Credentials> {
        val userId = sharedPreferences.getString(USER_KEY, null)
        val token = sharedPreferences.getString(TOKEN_KEY, null)

        if (userId != null && token != null) {
            return Credentials(userId, token).right()
        }

        return ErrorEntity.NO_CREDENTIALS_AVAILABLE.left()
    }

    override fun deleteCredentials() {
        sharedPreferences.edit {
            remove(USER_KEY)
            remove(TOKEN_KEY)
        }
    }
}