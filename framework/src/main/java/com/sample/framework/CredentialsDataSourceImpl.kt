package com.sample.framework

import android.content.SharedPreferences
import androidx.core.content.edit
import com.sample.domain.Credentials
import com.sample.domain.Result
import javax.inject.Inject

const val USER_KEY = "USER_KEY"
const val TOKEN_KEY = "TOKEN_KEY"
const val INVALID_USER_ID: Long = -1000

class CredentialsDataSourceImpl @Inject constructor(private val sharedPreferences: SharedPreferences) :
    com.sample.data.CredentialsDataSource {

    override suspend fun saveCredentials(credentials: Credentials) {
        sharedPreferences.edit {
            putLong(USER_KEY, credentials.userId)
            putString(TOKEN_KEY, credentials.token)
        }
    }

    override fun getCredentials(): Result<Credentials> {
        val userId = sharedPreferences.getLong(USER_KEY, INVALID_USER_ID)
        val token = sharedPreferences.getString(TOKEN_KEY, null)

        if (userId != INVALID_USER_ID && token != null) {
            return Result.Success(Credentials(userId, token))
        }

        return Result.Failure(com.sample.domain.ErrorEntity.NO_CREDENTIALS_AVAILABLE)
    }

    override fun deleteCredentials() {
        sharedPreferences.edit {
            remove(USER_KEY)
            remove(TOKEN_KEY)
        }
    }
}