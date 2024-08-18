package com.wypl.notification.infrastructure.exception

import com.wypl.notification.global.exception.GlobalException

class AuthClientException(errorCode: AuthClientErrorCode) : GlobalException(errorCode) {
}