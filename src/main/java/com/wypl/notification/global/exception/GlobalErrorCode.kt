package com.wypl.notification.global.exception

import org.springframework.http.HttpStatus

enum class GlobalErrorCode(
    private val statusCode: Int,
    private val errorCode: String,
    private val errorMessage: String
) : ErrorCode {
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(), "UNAUTHORIZED", "Unauthorized");

    override fun getStatusCode(): Int {
        return statusCode
    }

    override fun getErrorCode(): String {
        return errorCode
    }

    override fun getErrorMessage(): String {
        return errorMessage
    }
}