package com.sample.domain

sealed class Result<T> {
    data class Success<E>(val data: E): Result<E>()
    data class Failure<E>(val error: ErrorEntity) : Result<E>()
}

enum class ErrorEntity{
    WRONG_CREDENTIALS,
    NO_CREDENTIALS_AVAILABLE
}