package com.wypl.notification.global.exception

interface ErrorCode {
    fun getStatusCode(): Int
    fun getErrorCode(): String
    fun getErrorMessage(): String
}