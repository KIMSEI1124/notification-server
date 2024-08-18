package com.wypl.notification.emitter.kafka

import com.fasterxml.jackson.annotation.JsonProperty
import com.wypl.notification.global.kafka.KafkaProducerEvent

data class SuccessOfFailureEvent(
    @JsonProperty("recipient_id") val recipientId: Long,
    @JsonProperty("message") val message: String
) : KafkaProducerEvent()
