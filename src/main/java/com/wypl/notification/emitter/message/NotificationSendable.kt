package com.wypl.notification.emitter.message

interface NotificationSendable {
    fun getMessageType(): NotificationMessageType

    fun getData(): Map<String, Any>
}