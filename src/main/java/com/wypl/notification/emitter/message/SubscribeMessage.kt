package com.wypl.notification.emitter.message

data class SubscribeMessage(
    private val memberId: Int,
    private val timeout: Long,
    private val type: NotificationMessageType = NotificationMessageType.SUBSCRIBE
) : NotificationSendable {
    override fun getMessageType(): NotificationMessageType {
        return type
    }

    override fun getData(): Map<String, Any> {
        val map: MutableMap<String, Any> = HashMap()
        map["type"] = type
        map["timeout"] = timeout
        map["member_id"] = memberId
        return map
    }
}
