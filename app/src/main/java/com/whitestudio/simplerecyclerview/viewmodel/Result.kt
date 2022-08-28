package com.whitestudio.sidejo.viewmodel

open class Result<out T> {
    data class Success<out R>(val value: R) : Result<R>()
    data class Failure(
        val message: String?,
        val throwable: Throwable? = null
    ) : Result<Nothing>()

    data class Idle(
        val state: String? = ""
    ) : Result<Nothing>()
}