package com.wypl.notification.schedule.event

import com.fasterxml.jackson.annotation.JsonProperty
import com.wypl.notification.global.kafka.KafkaConsumerEvent

data class WriteScheduleReviewEvent(
    @JsonProperty("recipient_id") val recipientId: Long,
    @JsonProperty("nickname") val nickname: String,
    @JsonProperty("schedule_id") val scheduleId: Long,
    @JsonProperty("schedule_title") val scheduleTitle: String,
) : KafkaConsumerEvent()