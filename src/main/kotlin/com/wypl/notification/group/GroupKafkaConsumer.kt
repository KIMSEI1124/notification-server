package com.wypl.notification.group

import com.fasterxml.jackson.databind.ObjectMapper
import com.wypl.notification.group.event.GroupInviteEvent
import com.wypl.notification.group.event.GroupKafkaConsumerEventType
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {}

@Service
class GroupKafkaConsumer(
    val objectMapper: ObjectMapper,
) {
    @KafkaListener(topics = ["group"], groupId = "wypl-notification-group")
    fun listenGroupMessage(
        @Payload message: String,
        @Header(KafkaHeaders.RECEIVED_KEY) key: GroupKafkaConsumerEventType
    ) {
        when (key) {
            GroupKafkaConsumerEventType.GROUP_INVITE -> {
                val groupInviteMessage = objectMapper.readValue(message, GroupInviteEvent::class.java)
                logger.info { "REVIEW: $groupInviteMessage" }
            }
        }
    }
}