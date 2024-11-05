package com.tomasmacri.expensapp.domain.model.base

sealed class Operation<T>(
    var data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T, message: String? = null) : Operation<T>(data, message)

    class Error<T>(message: String, data: T? = null) : Operation<T>(data, message)

    class Loading<T> : Operation<T>()

}