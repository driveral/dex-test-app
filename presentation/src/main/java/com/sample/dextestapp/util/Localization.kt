package com.sample.dextestapp.util

import android.content.Context
import com.sample.dextestapp.R
import com.sample.domain.ErrorEntity

fun ErrorEntity.toLocalizedMessage(context: Context): String {
    return when (this) {
        ErrorEntity.WRONG_CREDENTIALS -> context.getString(R.string.error_invalid_credentials)
        ErrorEntity.NO_CREDENTIALS_AVAILABLE -> context.getString(R.string.error_no_credentials)
        ErrorEntity.USERNAME_IN_USE -> context.getString(R.string.error_username_in_use)
    }
}