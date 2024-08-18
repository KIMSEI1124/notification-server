package com.wypl.notification.group.event

import com.fasterxml.jackson.annotation.JsonProperty
import com.wypl.notification.global.kafka.KafkaConsumerEvent

data class GroupInviteEvent(
    @JsonProperty("sender_id") val senderId: Long,
    @JsonProperty("recipient_id") val recipientId: Long,
    @JsonProperty("nickname") val nickname: String,
    @JsonProperty("group_id") val groupId: Long,
    @JsonProperty("group_name") val groupName: String,
) : KafkaConsumerEvent()