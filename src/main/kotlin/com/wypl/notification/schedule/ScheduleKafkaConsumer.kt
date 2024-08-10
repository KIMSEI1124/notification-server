package com.wypl.notification.schedule

import com.fasterxml.jackson.databind.ObjectMapper
import com.wypl.notification.schedule.event.ScheduleKafkaConsumerEventType
import com.wypl.notification.schedule.event.WriteScheduleReviewEvent
import com.wypl.notification.schedule.service.ScheduleService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {}

@Service
class ScheduleKafkaConsumer(
    val objectMapper: ObjectMapper,
    val scheduleService: ScheduleService
) {
    @KafkaListener(topics = ["schedule"], groupId = "wypl-notification-group")
    fun listenScheduleMessage(
        @Payload message: String,
        @Header(KafkaHeaders.RECEIVED_KEY) key: ScheduleKafkaConsumerEventType
    ) {
        when (key) {
            ScheduleKafkaConsumerEventType.WRITE_REVIEW -> {
                val writeReviewMessage = objectMapper.readValue(message, WriteScheduleReviewEvent::class.java)
                scheduleService.processByWriteScheduleReviewEvent(writeReviewMessage)
            }
        }
    }
}