package com.wypl.notification.global.exception

open class GlobalException(
    private val errorCode: ErrorCode
) : RuntimeException() {

}