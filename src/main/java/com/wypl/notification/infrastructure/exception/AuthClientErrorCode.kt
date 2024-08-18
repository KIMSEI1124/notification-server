package com.wypl.notification.infrastructure.exception

import com.wypl.notification.global.exception.ErrorCode
import org.springframework.http.HttpStatus

enum class AuthClientErrorCode(
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